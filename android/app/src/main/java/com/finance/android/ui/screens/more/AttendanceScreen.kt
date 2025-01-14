package com.finance.android.ui.screens.more

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import com.finance.android.R
import com.finance.android.domain.dto.response.DailyAttendanceResponseDto
import com.finance.android.domain.service.DailyService
import com.finance.android.utils.Response
import com.finance.android.viewmodels.DailyViewModel
import com.kizitonwose.calendar.compose.HorizontalCalendar
import com.kizitonwose.calendar.compose.rememberCalendarState
import com.kizitonwose.calendar.core.CalendarDay
import com.kizitonwose.calendar.core.DayPosition
import com.kizitonwose.calendar.core.daysOfWeek
import dagger.hilt.android.lifecycle.HiltViewModel
import java.time.DayOfWeek
import java.time.YearMonth
import java.time.format.TextStyle
import java.util.*

@Preview
@Composable
fun ShowCalendar(dailyViewModel: DailyViewModel = hiltViewModel()) {
    val currentMonth = remember { YearMonth.now() }
    val startMonth = remember { currentMonth.minusMonths(100) } // Adjust as needed
    val endMonth = remember { currentMonth.plusMonths(100) } // Adjust as needed
    val daysOfWeek = daysOfWeek()
//    var attendanceList = mutableListOf<DailyAttendanceResponseDto>()
    val attendanceList = (dailyViewModel.attendanceList.value as Response.Success).data

    val state = rememberCalendarState(
        startMonth = startMonth,
        endMonth = endMonth,
        firstVisibleMonth = currentMonth,
        firstDayOfWeek = daysOfWeek.first()
    )

    BoxWithConstraints(
        modifier = Modifier
            .background(
                MaterialTheme.colorScheme.surface,
                RoundedCornerShape(dimensionResource(R.dimen.calendar_default))
            )
            .padding(dimensionResource(R.dimen.calendar_default))
    ) {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = currentMonth.monthValue.toString() + "월",
                fontSize = 20.sp,
                fontWeight = FontWeight.ExtraBold
            )
            Spacer(modifier = Modifier.size(dimensionResource(R.dimen.font_size_title_desc)))
            Text(text = "이번 달 출석한 횟수", fontWeight = FontWeight.SemiBold)
            Spacer(modifier = Modifier.size(dimensionResource(R.dimen.font_size_title_desc)))
            Text(text = "N", fontSize = 20.sp, fontWeight = FontWeight.ExtraBold)
            Spacer(modifier = Modifier.size(dimensionResource(R.dimen.font_size_title_desc)))
            DaysOfWeekTitle(daysOfWeek = daysOfWeek)
            Spacer(modifier = Modifier.size(dimensionResource(R.dimen.padding_medium)))
            HorizontalCalendar(
                state = state,
                dayContent = { Day(it, attendanceList) }
            )
        }
    }
}

@Composable
fun DaysOfWeekTitle(daysOfWeek: List<DayOfWeek>) {
    Row(modifier = Modifier.fillMaxWidth()) {
        for (dayOfWeek in daysOfWeek) {
            Text(
                modifier = Modifier.weight(1f),
                textAlign = TextAlign.Center,
                text = dayOfWeek.getDisplayName(TextStyle.SHORT, Locale.getDefault()),
                color = Color.Gray
            )
        }
    }
}

@Composable
fun Day(day: CalendarDay, attendanceList : MutableList<DailyAttendanceResponseDto>) {
    Box(
        modifier = Modifier
            .aspectRatio(1f), // This is important for square sizing!
        contentAlignment = Alignment.Center
    ) {
        if (day.position == DayPosition.MonthDate && attendanceList[day.date.dayOfMonth - 1].attendance) {
            Image(
                painter = painterResource(R.drawable.paw),
                contentDescription = null, // 필수 param
            )
        } else {
            Text(
                text = day.date.dayOfMonth.toString(),
                color = if (day.position == DayPosition.MonthDate) Color.Black else Color.White
            )
        }
    }
}