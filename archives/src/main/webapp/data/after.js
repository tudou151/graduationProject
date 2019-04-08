var date;
var year;var month;var day;var week;var hour;var minute;var second;var t;
showTime();
function showTime(){
    date = new Date();
    year = date.getFullYear();
    month = date.getMonth()+1;
    month = ("0" + (month)).slice(-2);
    day = date.getDate();
    day = ("0" + (day)).slice(-2);
    week = date.getDay();
    switch (week){
        case 1 : week =  "星期一";break;
        case 2 : week =  "星期二";break;
        case 3 : week =  "星期三";break;
        case 4 : week =  "星期四";break;
        case 5 : week =  "星期五";break;
        case 6 : week =  "星期六";break;
        case 0 : week =  "星期日";break;
        default:week="未知";break;
    }
    hour = date.getHours();
    hour = ("0" + (hour)).slice(-2);
    minute = date.getMinutes();
    minute = ("0" + (minute)).slice(-2);
    second = date.getSeconds();
    second = ("0" + (second)).slice(-2);
    document.getElementById("showNowTime").innerHTML=year+"-"+month+"-"+day+"（"+week+"）"+hour+":"+minute+":"+second;
    t=setTimeout("showTime()",1000);
}
