var url=window.location.href;
url = url.substring(0,url.indexOf("/",10));

//检测当前是否有员工登录
function login_yesOrNo() {
    ajaxRequest("GET",url+"/employee/loginYesOrNo",login_yesOrNo_handle,"text","");
}
function login_yesOrNo_handle(data) {
    var data=eval("("+data+")");
    if(data['code']!=0){
        alert("未检测到登录用户");
        location.href=url+"/page/login";
    }
}

//点击退出登录
function out_System(){
    out_img_style();
    location.href=url+"/page/login";
}
var checkbox_id;
var select_YesOrNo=0;
//选中或取消全部复选框
function select_all(num) {
    var current_num=1;
    if (select_YesOrNo==0){
        while(current_num<=num){
            checkbox_id = "checkbox_"+current_num;
            document.getElementById(checkbox_id).checked = true;
            current_num++;
        }
        select_YesOrNo=1;
    }else{
        while(current_num<=num){
            checkbox_id = "checkbox_"+current_num;
            document.getElementById(checkbox_id).checked = false;
            current_num++;
        }
        select_YesOrNo=0;
    }
}
//鼠标移出‘退出登录'图标上的样式
function out_img_style() {
    document.getElementById("out_img_div").style.width="23px";
    document.getElementById("out_img_div").style.height="19px";
}
//鼠标移入‘退出登录'图标上的样式
function out_img_1() {
    document.getElementById("out_img_div").style.width="25px";
    document.getElementById("out_img_div").style.height="21px";
}
//鼠标移入 用户头像 上的样式
function headPortrait_in(id) {
    document.getElementById(id).style.width="52px";
    document.getElementById(id).style.height="52px";
}
//鼠标移出 用户头像 上的样式
function headPortrait_out(id) {
    document.getElementById(id).style.width="50px";
    document.getElementById(id).style.height="50px";
}
//鼠标移入 隐藏菜单 时的样式
var yincang_div;
function onmouseover_yincang_div(id) {
    yincang_div=document.getElementById(id);
    yincang_div.style.width="32px";
    yincang_div.style.height="28px";
}
//鼠标移出 隐藏菜单 时的样式
function onmouseout_yincang_div(id) {
    yincang_div=document.getElementById(id);
    yincang_div.style.width="30px";
    yincang_div.style.height="26px";
}
//鼠标点击 隐藏菜单
function click_yincang_div(id) {
    var show = document.getElementById(id);
    if (show.style.display){
        show.style.display="";
        document.getElementById('rightDiv').style.width="84.8%";
    }else{
        show.style.display="none";
        document.getElementById('rightDiv').style.width="100%";
    }
}
//login页面
//点击登录按钮
function submit_form(id) {
    var employeeNumber = document.getElementById(id).employeeNumber.value;
    var password = document.getElementById(id).password.value;
    var msg = document.getElementById("show_pageMsg_span");
    if (employeeNumber.trim(" ")=="" || password.trim(" ")==""){
        msg.innerHTML="工号或密码不能为空";
    }else if(password.indexOf(" ")!=-1 || employeeNumber.indexOf(" ")!=-1){
        msg.innerHTML="用户名和密码不能包含空格";
    }else{
        msg.innerHTML="";
        document.getElementById(id).submit();
    }
}
//鼠标移入 登录按钮 的样式
function login_btn_div_over(id) {
    document.getElementById(id).style.backgroundColor='#0E959C';
}
//鼠标移出 登录按钮 的样式
function login_btn_div_out(id) {
    document.getElementById(id).style.backgroundColor='#12B3BA';
}
//鼠标按下 登录按钮 的样式
function login_btn_down(id) {
    document.getElementById(id).style.backgroundColor='#0B7479';
}
//鼠标松开 登录按钮 的样式
function login_btn_up(id) {
    document.getElementById(id).style.backgroundColor='#0E959C';
}

//鼠标移入 表格按钮 的样式
function login_btn_td_over(id) {
    document.getElementById(id).style.color='#30B8BE';
}
//鼠标移出 表格按钮 的样式
function login_btn_td_out(id) {
    document.getElementById(id).style.color='#12B3BA';
}



//鼠标移入左侧功能栏的样式
var temporary1;
function leftOnMouseOver(id){

    temporary1=id;
    var img_div=document.getElementById(temporary1);
    img_div.style.backgroundColor="#D1DBE5";
}
//鼠标移出左侧功能栏的样式
function leftOnMouseOut(id) {

    temporary1=id;
    var img_div=document.getElementById(temporary1);
    img_div.style.backgroundColor="";
}
//鼠标点击选择一个左侧功能栏的功能的交换样式————下拉列表展开
var currentShowWindow_id = "";
var now_img_id = "";
var span_id = "div_span_";
var now_show_window_id = "window_div_0";
function selectWindow(id) {

    if (currentShowWindow_id!=id){
        document.getElementById('top_div_1').style.display='';
        if (currentShowWindow_id!=""){
            document.getElementById(currentShowWindow_id).src = "../images/index/"+now_img_id+".png";
            document.getElementById(span_id).style.color="";
        }
        currentShowWindow_id = id;
        span_id = "div_span_";
        span_id = span_id + id.slice(9);
        now_span_id = id;
        document.getElementById(span_id).style.color="blue";

        showWindow(id.slice(9));
    }
}
//鼠标点击员工信息左侧功能栏的功能的交换样式————下拉列表展开
var currentShowWindow_id = "";
var img_url = "../images/index/";
var now_img_id = "";
var span_id = "div_span_";
var img_id = "div_img_";
var now_show_window_id = "window_div_0";
function employeeSelectWindow(id) {

    if (currentShowWindow_id!=id){
        document.getElementById('top_div_1').style.display='';
        if (currentShowWindow_id!=""){
            document.getElementById(currentShowWindow_id).src = "../images/index/"+now_img_id+".png";
            document.getElementById(span_id).style.color="";
        }
        currentShowWindow_id = id;
        span_id = "div_span_";
        img_url = "../images/index/";
        img_url = img_url + "arrows_down.png";
        span_id = span_id + id.slice(9);
        img_id = "div_img_";
        img_id =img_id + id.slice(9);
        now_span_id = id;
        // document.getElementById(span_id).style='';
        //document.getElementById(span_id).style.color="blue";
        document.getElementById(img_id).src = img_url;
        showWindow(id.slice(9));

    }
}
//鼠标点击选择一个左侧功能栏的功能的交换样式————下拉列表收起
function packWindow(id) {
    var currentShowWindow_id = "";
    var img_url = "../images/index/";
    var now_img_id = "";
    var span_id = "div_span_";
    var img_id = "div_img_";
    if (currentShowWindow_id!=id){
        document.getElementById('top_div_1').style.display='';
        if (currentShowWindow_id!=""){
            document.getElementById(currentShowWindow_id).src = "../images/index/"+now_img_id+"_down.png";
            document.getElementById(span_id).style.color="red";
        }
        span_id = "div_span_";
        img_url = "../images/index/";
        img_url = img_url + "arrows.png";
        span_id = span_id + id.slice(9);
        img_id = "div_img_";
        img_id =img_id + id.slice(9);
         //alert("dadede "+img_url);
        now_span_id = id;
        document.getElementById(span_id).style.color="black";
        document.getElementById(img_id).src = img_url;

        showWindow(0);
    }
}

//展示右侧操作窗口
function showWindow(num){
    document.getElementById(now_show_window_id).style.display='none';
    now_show_window_id = "window_div_"+num;
    document.getElementById(now_show_window_id).style.display="";
    //alert(now_span_id);
    if (now_span_id=="div_span_1"){
        login_yesOrNo();
        ajaxRequest("GET",url+"/employee/getUpdateEmployeeInfo",update_employees_div,"text","");
    }else if(now_span_id=="div_span_2"){
        login_yesOrNo();
        ajaxRequest("GET",url+"/Department/getAllDepartments",update_department_div,"text","");
    }
    else if(now_span_id=="div_span_3"){
        login_yesOrNo();
        ajaxRequest("GET",url+"/Cultivate/getAllCultivates",update_cultivate_div,"text","");
    }else if(now_span_id=="div_span_4"){
        login_yesOrNo();
        ajaxRequest("GET",url+"/employee/getMyAccount",update_myAccount_div,"text","");
    }else if(now_span_id=="div_span_5"){
        login_yesOrNo();
        ajaxRequest("GET",url+"/BulletinResource/getAllBulletinResources",update_bulletin_div,"text","");
    }else if(now_span_id=="div_span_6"){
        login_yesOrNo();
        ajaxRequest("GET",url+"/Certificate/getAllCertificates",update_certificate_div,"text","");
    }else if(now_span_id=="div_span_7"){
        login_yesOrNo();
        ajaxRequest("GET",url+"/Title/getAllTitles",update_title_div,"text","");
    }/*else if(now_span_id=="div_span_8"){
        login_yesOrNo();
        ajaxRequest("GET",url+"/Certificate/getAllCertificates",update_certificate_div,"text","");
    }*//*else if(now_span_id=="div_span_9"){
        login_yesOrNo();
        ajaxRequest("GET",url+"/BulletinResource/getAllBulletinResources",update_bulletin_div,"text","");
    }*/else if(now_span_id=="div_span_10"){
        login_yesOrNo();
        ajaxRequest("GET",url+"/Education/getAllEducations",update_education_div,"text","");
    }else if(now_span_id=="div_span_11"){
        login_yesOrNo();
        ajaxRequest("GET",url+"/Community/getAllCommunitys",update_community_div,"text","");
    }else if(now_span_id=="div_span_12"){
        login_yesOrNo();
        ajaxRequest("GET",url+"/Experience/getAllExperiences",update_experience_div,"text","");
    }
    else if(now_span_id=="div_span_13"){
        login_yesOrNo();
        ajaxRequest("GET",url+"/AwardAndPunish/getAllAwardAndPunishs",update_awardAndPunish_div,"text","");
    }else{
        document.getElementById('top_div_1').style.display='none';
    }
}

//点击展开关闭效果
function openShutManager(oSourceObj,oTargetObj,shutAble,oOpenTip,oShutTip){
    var div_span = "div_span_";
    var sourceObj = typeof oSourceObj == "string" ? document.getElementById(oSourceObj) : oSourceObj;
    var targetObj = typeof oTargetObj == "string" ? document.getElementById(oTargetObj) : oTargetObj;
    var openTip = oOpenTip || "";
    var shutTip = oShutTip || "";
    if(targetObj.style.display!="none"){
        div_span =  "div_span_";
        div_span = div_span + oTargetObj.slice(9);

        if(shutAble){
            return;
        }

        targetObj.style.display="none";
        packWindow(div_span);
        if(openTip  && shutTip){
            sourceObj.innerHTML = shutTip;

        }
    } else {
        div_span =  "div_span_";
        div_span = div_span + oTargetObj.slice(9);
        employeeSelectWindow(div_span);
        targetObj.style.display="block";
        if(openTip  &&  shutTip){
            sourceObj.innerHTML = openTip;

        }
    }
}
//根据姓名查询
function selectEmployee_byEmployeeName(inputId) {
    login_yesOrNo();
    var input = document.getElementById(inputId);
    var employeeName = input.employeeName.value;
    var param="employeeName="+employeeName;
    ajaxRequest("POST",url+"/employee/getEmployeeByName",select_employee_handle,"text",param);
}

function select_employee_handle(data) {
    login_yesOrNo();
    document.getElementById('top_div_2').style.display="none";
    var data = eval("("+data+")");
    if (data['code']==0) {
        var employeeNumber = data['data']['employeeNumber'];
        ajaxRequest("GET",url+"/employee/getPersonByEmployeeNumber?employeeNumber="+employeeNumber,update_employees_div,"text","");
    }else{
        document.getElementById('top_div_1').style.display="none";
        alert(data['msg']);
    }
}

function update_employees_div(data) {
    document.getElementById('page_div').innerHTML=data;
    document.getElementById('top_div_1').style.display='none';
}
//按下  上一页
function upPage(currentPage) {
    document.getElementById('top_div_1').style.display='';
    currentPage--;
    login_yesOrNo();
    ajaxRequest("GET",url+"/employee/getPage?currentPage="+currentPage,update_employees_div,"text","");
}
//按下  下一页
function downPage(currentPage) {
    document.getElementById('top_div_1').style.display='';
    ++currentPage;
    login_yesOrNo();
    ajaxRequest("GET",url+"/employee/getPage?currentPage="+currentPage,update_employees_div,"text","");
}
//跳转到指定页面
function kipPage(id,allPageNum,currentPage) {
    var needPage = document.getElementById(id).value;
    login_yesOrNo();
    if (needPage>allPageNum){
        alert("请输入页数范围内的页");
        document.getElementById(id).value=currentPage;
    }else{
        ajaxRequest("GET",url+"/employee/getPage?currentPage="+currentPage,update_employees_div,"text","");
    }
}
//删除员工
function del_employee(num) {
    document.getElementById('top_div_1').style.display='';
    var ids = new Array();
    var ids_num=1;
    var add_String_Array=0;
    while(ids_num<=num){
        checkbox_id = "checkbox_"+ids_num;
        if (document.getElementById(checkbox_id).checked){
            ids[add_String_Array]=""+document.getElementById(checkbox_id).value;
            add_String_Array++;
        }
        ids_num++;
    }

    if(ids.length!=0){
        login_yesOrNo();
        if(confirm("确定删除？")){
            ajaxRequest("POST",url+"/employee/del_employees",update_employees_div,"text","ids="+ids.toString());
        }else{
            document.getElementById('top_div_1').style.display='none';
        }
    }else{
        document.getElementById('top_div_1').style.display='none';
        alert("请选择要删除的数据");
    }
}
//弹出添加员工面板
function add_employee(id) {
    document.getElementById('top_div_2').style.display="";
    document.getElementById(id).style.display="";
}
//确定添加员工
function determine_add_employee(id) {
    var form = document.getElementById(id);
    var employeeNumber=form.employeeNumber.value;
    var employeeName=form.employeeName.value;
    var sex=form.sex.value;
    var tel=form.tel.value;
    var ifOnDuty=form.ifOnDuty.value;
    var permissions=form.permissions.value;
    var idCard=form.idCrad.value;

    var birth= form.birth.value;
    var zzmm= form.zzmm.value;
    var nation= form.nation.value;
    var marriage= form.marriage.value;
    var email= form.email.value;
    var root= form.root.value;
    var address= form.address.value;
    var beginTime= form.beginTime.value;
    var becomeTime= form.becomeTime.value;
    var leaveTime=form.leaveTime.value;
    var departmentName = form.departmentName.value;
    if (employeeNumber=="" || employeeName=="" || sex=="" || ifOnDuty=="" || permissions=="" || idCard=="" || address=="" || beginTime==""){
        alert("员工的编号、姓名、任职状态、性别、权限、证件号码、地址、入职时间为必填");
    }else if (tel.length!=11){
        alert("请输入符合规范的电话号码");
    }else if(isCardID(idCard)){
        var param = "employeeNumber="+employeeNumber+"@#$departmentName="+ departmentName+"@#$employeeName="+employeeName+"@#$sex="+sex+"@#$ifOnDuty="+ifOnDuty+"@#$permissions="+permissions+"@#$tel="+tel+"@#$idCard="+idCard+"@#$birth=" +birth + "@#$zzmm=" +zzmm + "@#$nation=" + nation + "@#$marriage=" +marriage + "@#$email=" +email + "@#$root=" +root + "@#$address=" +address+ "@#$beginTime=" +beginTime + "@#$becomeTime=" +becomeTime + "@#$leaveTime=" +leaveTime;
        document.getElementById('add_employee_panel').style.display='none';
        document.getElementById('top_div_2').style.display='none';
        document.getElementById('top_div_1').style.display="";
        login_yesOrNo();
        ajaxRequest("POST",url+"/employee/addEmployee",add_employee_again,"text",param);
    }
}
//判断身份证号码是否合法
function isCardID(sId){
    var iSum=0 ;
    var info="" ;
    if(!/^\d{17}(\d|x)$/i.test(sId)){alert("你输入的身份证长度或格式错误");return false;}
    sId=sId.replace(/x$/i,"a");
    if(aCity[parseInt(sId.substr(0,2))]==null){alert("你的身份证地区非法");return false;}
    sBirthday=sId.substr(6,4)+"-"+Number(sId.substr(10,2))+"-"+Number(sId.substr(12,2));
    var d=new Date(sBirthday.replace(/-/g,"/")) ;
    if(sBirthday!=(d.getFullYear()+"-"+ (d.getMonth()+1) + "-" + d.getDate())){alert("身份证上的出生日期非法");return false;}
    for(var i = 17;i>=0;i --){iSum += (Math.pow(2,i) % 11) * parseInt(sId.charAt(17 - i),11) ;}
    if(iSum%11!=1){alert("你输入的身份证号非法");return false;}
    //aCity[parseInt(sId.substr(0,2))]+","+sBirthday+","+(sId.substr(16,1)%2?"男":"女");//此次还可以判断出输入的身份证号的人性别
    return true;
}
var aCity={11:"北京",12:"天津",13:"河北",14:"山西",15:"内蒙古",21:"辽宁",22:"吉林",23:"黑龙江",31:"上海",32:"江苏",33:"浙江",34:"安徽",35:"福建",36:"江西",37:"山东",41:"河南",42:"湖北",43:"湖南",44:"广东",45:"广西",46:"海南",50:"重庆",51:"四川",52:"贵州",53:"云南",54:"西藏",61:"陕西",62:"甘肃",63:"青海",64:"宁夏",65:"新疆",71:"台湾",81:"香港",82:"澳门",91:"国外"}
//添加员工的处理结果
function add_employee_again(data) {
    var data = eval("("+data+")");
    if(data['code']==0){
        alert("添加成功");
        login_yesOrNo();
        ajaxRequest("GET",url+"/employee/getUpdateEmployeeInfo",update_employees_div,"text","");
    }else{
        document.getElementById('top_div_1').style.display="none";
        alert(data['msg']);
    }
}
//添加员工面板的关闭按钮
function close_addEmployee_panel(id) {
    document.getElementById('add_employee_form').reset();
    document.getElementById(id).style.display='none';
    document.getElementById('top_div_2').style.display="none";
}

//查看员工信息
function look_employee(num) {
    document.getElementById('top_div_1').style.display="";
    ajaxRequest("GET",url+"/employee/getEmployeeByEmployeeNumber?employeeNumber="+num,getEmployeeByEmployeeNumber,"text","");
}

//将要查看的数据输出到查看面板上
function getEmployeeByEmployeeNumber(data) {
    var data = eval("("+data+")");
    if(data['code']==0){
        document.getElementById('top_div_1').style.display="none";
        document.getElementById('top_div_2').style.display="";
        document.getElementById('look_employee_panel').style.display="";
        var look_form=document.getElementById('look_employee_form');
        look_form.e_id.value=data['data']['id'];
        look_form.employeeNumber.value=data['data']['employeeNumber'];
        look_form.employeeName.value=data['data']['employeeName'];
        look_form.sex.value=data['data']['sex'];
        look_form.tel.value=data['data']['tel'];
        look_form.idCard.value=data['data']['idCard'];

        look_form.birth.value=data['data']['birth'];
        look_form.zzmm.value=data['data']['zzmm'];
        look_form.nation.value=data['data']['nation'];
        look_form.marriage.value=data['data']['marriage'];
        look_form.email.value=data['data']['email'];
        look_form.root.value=data['data']['root'];
        look_form.address.value=data['data']['address'];
        look_form.beginTime.value=data['data']['beginTime'];
        look_form.becomeTime.value=data['data']['becomeTime'];
        look_form.leaveTime.value=data['data']['leaveTime'];
        look_form.departmentName.value = data['data']['departmentName'];

        if (data['data']['ifOnDuty']=="在职"){
            document.getElementById('look_ifOnDuty_input').options[0].selected=true;
        }else{
            document.getElementById('look_ifOnDuty_input').options[1].selected=true;
        }
        if (data['data']['permissions']==0){
            document.getElementById('look_permissions_input').options[0].selected=true;
        }else if(data['data']['permissions']==1){
            document.getElementById('look_permissions_input').options[1].selected=true;
        }else{
            document.getElementById('look_permissions_input').options[2].selected=true;
        }
        if (data['data']['marriage']=="已婚"){
            document.getElementById('look_marriage_input').options[0].selected=true;
        }else if(data['data']['marriage']=="未婚"){
            document.getElementById('look_marriage_input').options[1].selected=true;
        }else{
            document.getElementById('look_marriage_input').options[2].selected=true;
        }

    }else{
        document.getElementById('top_div_1').style.display="none";
        alert("发生未知错误");
    }
}

//修改员工信息按钮
function update_employee(num) {
    document.getElementById('top_div_1').style.display="";
    var ids_num1=1;
    var YN_greater_one=0;
    var empl_id1;
    while (ids_num1<=num){
        checkbox_id = "checkbox_"+ids_num1;
        if (document.getElementById(checkbox_id).checked){
            if (YN_greater_one==0){
                empl_id1 = document.getElementById(checkbox_id).value;
                YN_greater_one++;
            }else{
                YN_greater_one=-1;
                break;
            }
        }
        ids_num1++;
    }
    if (YN_greater_one==1){
        login_yesOrNo();
        ajaxRequest("GET",url+"/employee/getEmployee?id="+empl_id1,getEmployeeById,"text","");
    }else{
        document.getElementById('top_div_1').style.display="none";
        alert("请选择一条数据进行修改");
    }
}
//将要修改的数据输出到修改面板上
function getEmployeeById(data) {
    var data = eval("("+data+")");
    if(data['code']==0){
        document.getElementById('top_div_1').style.display="none";
        document.getElementById('top_div_2').style.display="";
        document.getElementById('update_employee_panel').style.display="";
        var update_form=document.getElementById('update_employee_form');
        update_form.e_id.value=data['data']['id'];
        update_form.employeeNumber.value=data['data']['employeeNumber'];
        update_form.employeeName.value=data['data']['employeeName'];
        update_form.sex.value=data['data']['sex'];
        update_form.tel.value=data['data']['tel'];
        update_form.idCard.value=data['data']['idCard'];

        update_form.birth.value=data['data']['birth'];
        update_form.zzmm.value=data['data']['zzmm'];
        update_form.nation.value=data['data']['nation'];
        update_form.marriage.value=data['data']['marriage'];
        update_form.email.value=data['data']['email'];
        update_form.root.value=data['data']['root'];
        update_form.address.value=data['data']['address'];
        update_form.beginTime.value=data['data']['beginTime'];
        update_form.becomeTime.value=data['data']['becomeTime'];
        update_form.leaveTime.value=data['data']['leaveTime'];
        update_form.departmentName.value = data['data']['departmentName'];
        if (data['data']['ifOnDuty']=="在职"){
            document.getElementById('update_ifOnDuty_input').options[0].selected=true;
        }else{
            document.getElementById('update_ifOnDuty_input').options[1].selected=true;
        }
        if (data['data']['permissions']==0){
            document.getElementById('update_permissions_input').options[0].selected=true;
        }else if(data['data']['permissions']==1){
            document.getElementById('update_permissions_input').options[1].selected=true;
        }else{
            document.getElementById('update_permissions_input').options[2].selected=true;
        }
        if (data['data']['marriage']=="已婚"){
            document.getElementById('update_marriage_input').options[0].selected=true;
        }else if(data['data']['marriage']=="未婚"){
            document.getElementById('update_marriage_input').options[1].selected=true;
        }else{
            document.getElementById('update_marriage_input').options[2].selected=true;
        }
    }else{
        document.getElementById('top_div_1').style.display="none";
        alert("发生未知错误");
    }
}

//确定修改员工信息按钮
function determine_update_employee(id) {
    var update_form = document.getElementById(id);
    var id=update_form.e_id.value;
    var employeeNumber=update_form.employeeNumber.value;
    var employeeName=update_form.employeeName.value;
    var sex=update_form.sex.value;
    var ifOnDuty=update_form.ifOnDuty.value;
    var permissions=update_form.permissions.value;
    var tel=update_form.tel.value;
    var idCard=update_form.idCard.value;

    var birth= update_form.birth.value;
    var zzmm= update_form.zzmm.value;
    var nation= update_form.nation.value;
    var marriage= update_form.marriage.value;
    var email= update_form.email.value;
    var root= update_form.root.value;
    var address= update_form.address.value;
    var beginTime= update_form.beginTime.value;
    var becomeTime= update_form.becomeTime.value;
    var leaveTime=update_form.leaveTime.value;
    var departmentName= update_form.departmentName.value;

    if (employeeNumber=="" || employeeName=="" || sex=="" || ifOnDuty=="" || permissions=="" || idCard=="" || departmentName=="" || beginTime==""){
        alert("员工的编号、姓名、任职状态、性别、权限、证件号码、所属部门、入职时间为必填");
    }else if (tel.length!=11){
        alert("请输入符合规范的电话号码");
    }else if(isCardID(idCard)){
        var param = "employeeNumber="+employeeNumber+"@#$departmentName="+ departmentName+"@#$employeeName="+employeeName+"@#$sex="+sex+"@#$ifOnDuty="+ifOnDuty+"@#$permissions="+permissions+"@#$tel="+tel+"@#$idCard="+idCard+"@#$birth=" +birth + "@#$zzmm=" +zzmm + "@#$nation=" + nation + "@#$marriage=" +marriage + "@#$email=" +email + "@#$root=" +root + "@#$address=" +address+ "@#$beginTime=" +beginTime + "@#$becomeTime=" +becomeTime + "@#$leaveTime=" +leaveTime + "@#$id=" + id ;
        // login_yesOrNo();
        ajaxRequest("POST",url+"/employee/updateEmployeeById",update_employee_again,"text",param);
    }
}
//修改数据提交的返回结果处理
function update_employee_again(data) {
    var data = eval("("+data+")");
    if (data['code']==0){
        alert("修改成功");
        document.getElementById('top_div_2').style.display="none";
        document.getElementById('update_employee_panel').style.display="none";
        login_yesOrNo();
        ajaxRequest("GET",url+"/employee/getUpdateEmployeeInfo",update_employees_div,"text","");
    }else{
        alert(data['msg']);
    }
}
//修改员工面板的关闭按钮
function close_updateEmployee_panel(id) {
    document.getElementById('update_employee_form').reset();
    document.getElementById(id).style.display='none';
    document.getElementById('top_div_2').style.display="none";
}
//更新我的账户面板中的内容
function update_myAccount_div(data) {
    document.getElementById('a_div_id_06').innerHTML=data;
    document.getElementById('top_div_1').style.display='none';
}
//打开 修改员工资料面板
function modify_employee(num) {
    login_yesOrNo();
    ajaxRequest("POST",url+"/employee/getEmployeeByNumber",modify_employee_handle,"text","employeeNumber="+num);
}
//根据员工号查询的员工信息 在修改资料面板中展示
function modify_employee_handle(data) {
    var data=eval("("+data+")");
    if (data['code']==0){
        document.getElementById('top_div_2').style.display='';
        document.getElementById('a_div_id_07').style.display='';
        var a0_form = document.getElementById('a_div_id_08');
        a0_form.employeeNumber.value=data['data']['employeeNumber'];
        a0_form.employeeName.value=data['data']['employeeName'];
        a0_form.tel.value=data['data']['tel'];
        a0_form.idCard.value=data['data']['idCard'];
        a0_form.sex.value=data['data']['sex'];

        a0_form.birth.value=data['data']['birth'];
        a0_form.zzmm.value=data['data']['zzmm'];
        a0_form.nation.value=data['data']['nation'];
        a0_form.marriage.value=data['data']['marriage'];
        a0_form.email.value=data['data']['email'];
        a0_form.root.value=data['data']['root'];
        a0_form.address.value=data['data']['address'];
        a0_form.beginTime.value=data['data']['beginTime'];
        a0_form.becomeTime.value=data['data']['becomeTime'];
        a0_form.leaveTime.value=data['data']['leaveTime'];
        a0_form.departmentName.value = data['data']['departmentName'];

        if (data['data']['ifOnDuty']=="在职"){
            document.getElementById('a_div_id_11').options[0].selected=true;
        }else{
            document.getElementById('a_div_id_11').options[1].selected=true;
        }
        if (data['data']['permissions']==0){
            document.getElementById('a_div_id_10').options[0].selected=true;
        }else if(data['data']['permissions']==1){
            document.getElementById('a_div_id_10').options[1].selected=true;
        }else{
            document.getElementById('a_div_id_10').options[2].selected=true;
        }
        if (data['data']['marriage']=="已婚"){
            document.getElementById('updateMyAccount_marriage_input').options[0].selected=true;
        }else if(data['data']['marriage']=="未婚"){
            document.getElementById('updateMyAccount_marriage_input').options[1].selected=true;
        }else{
            document.getElementById('updateMyAccount_marriage_input').options[2].selected=true;
        }
    }else{
        alert(data['msg']);
    }
}
//确定修改员工资料按钮
function determine_modify_employee(formId) {
    var a1_form = document.getElementById(formId);
    var employeeNumber=a1_form.employeeNumber.value;
    var employeeName=a1_form.employeeName.value;
    var sex=a1_form.sex.value;
    var tel=a1_form.tel.value;
    var idCard=a1_form.idCard.value;

    var birth= a1_form.birth.value;
    var zzmm= a1_form.zzmm.value;
    var nation= a1_form.nation.value;
    var marriage= a1_form.marriage.value;
    var email= a1_form.email.value;
    var root= a1_form.root.value;
    var address= a1_form.address.value;
    var beginTime= a1_form.beginTime.value;
    var becomeTime= a1_form.becomeTime.value;
    var leaveTime=a1_form.leaveTime.value;
    var departmentName = a1_form.departmentName.value;

    if (departmentName=="" || employeeNumber=="" || employeeName=="" || sex=="" || idCard=="" || beginTime==""){
        alert("员工的编号、姓名、性别、证件号码、所属部门、入职时间为必填");
    }else if (tel.length!=11){
        alert("请输入符合规范的电话号码");
    }else if(isCardID(idCard)){
        login_yesOrNo();
        var param = "employeeName="+employeeName+"@#$departmentName="+ departmentName + "@#$sex="+sex+"@#$tel="+tel+"@#$idCard="+idCard+"@#$employeeNumber="+employeeNumber+"@#$birth=" +birth + "@#$zzmm=" +zzmm + "@#$nation=" + nation + "@#$marriage=" +marriage + "@#$email=" +email + "@#$root=" +root + "@#$address=" +address+ "@#$beginTime=" +beginTime + "@#$becomeTime=" +becomeTime + "@#$leaveTime=" +leaveTime;
        ajaxRequest("POST",url+"/employee/updateEmployeeByNumber",determine_modify_employee_again,"text",param);
    }
}
function determine_modify_employee_again(data) {
    var data=eval("("+data+")");
    if (data['code']==0){
        document.getElementById('top_div_2').style.display='none';
        document.getElementById('a_div_id_07').style.display='none';
        alert("修改成功");
        document.getElementById('top_div_1').style.display='';
        login_yesOrNo();
        ajaxRequest("GET",url+"/employee/getMyAccount",update_myAccount_div,"text","");
    }else{
        alert(data['msg']);
    }
}
//关闭 修改员工资料面板
function close_update_panel1(id) {
    document.getElementById('a_div_id_08').reset();
    document.getElementById(id).style.display='none';
    document.getElementById('top_div_2').style.display="none";
}
//正在显示的窗口
var a0_variable_id="a_div_id_05";
//员工修改资料面板选择操作窗口
function select_window01(id) {
    document.getElementById('a_div_id_05').style.display="none";
    if(id=="a_div_id_05"){
        document.getElementById(a0_variable_id).style.display="none";
        document.getElementById(id).style.display="";
        a0_variable_id=id;
    }else if(id=="a_div_id_12"){
        document.getElementById(a0_variable_id).style.display="none";
        document.getElementById(id).style.display="";
        a0_variable_id=id;
    }else if(id=="a_div_id_16"){
        document.getElementById(a0_variable_id).style.display="none";
        document.getElementById(id).style.display="";
        a0_variable_id=id;
    }else{
        document.getElementById(a0_variable_id).style.display="none";
        document.getElementById(id).style.display="";
        a0_variable_id=id;
    }
}
//读取选择的图片
function imgPreview(fileDom){
    //判断是否支持FileReader
    if (window.FileReader) {
        var reader = new FileReader();
    } else {
        alert("您的设备不支持图片预览功能，如需该功能请升级您的设备！");
    }
    //获取文件
    var file = fileDom.files[0];
    var imageType = /^image\//;
    //是否是图片
    if (!imageType.test(file.type)) {
        alert("请选择图片！");
        return;
    }
    //读取完成
    reader.onload = function(e) {
        //获取图片dom
        var img = document.getElementById("a_div_id_14");
        //图片路径设置为读取的图片
        img.src = e.target.result;
    };
    reader.readAsDataURL(file);
    reader.readAsText(file,"");
}
//上传图片
/*function upload() {
    var xhr = new XMLHttpRequest();
    var progress = document.getElementById("progress")
    progress.style.display = "block";

    xhr.upload.addEventListener("progress", function(e) {
        if (e.lengthComputable) {
            var percentage = Math.round((e.loaded * 100) / e.total);
            progress.value = percentage;
        }
    }, false);

    xhr.upload.addEventListener("load", function(e){
        console.log("上传完毕...")
    }, false);

    xhr.open("POST", url+"/employee/uploadMyImage");
    xhr.overrideMimeType('text/plain; charset=x-user-defined-binary');
    xhr.onreadystatechange = function() {
        if (xhr.readyState == 4 && xhr.status == 200) {
            alert(xhr.responseText); // handle response.
            progress.style.display = "none";
            progress.value = 0;
        }
    };
    var file = document.getElementById("imgFile");
    var fd = new FormData();
    fd.append(file.files[0].name, file.files[0]);
    xhr.send(fd);
}*/
function upload() {
    login_yesOrNo();
    var file = document.getElementById('a1_form_input').files[0];
    var img = document.getElementById('a_div_id_14');
    if(file){
        ajaxRequest("POST",url+"/employee/uploadMyImage",image_handle,"text","");
    }else{
        alert("请选择图片");
    }
}
function image_handle(data) {
    var data = eval("("+data+")");
    if (data['code']==0){
        alert("上传成功");
    }else{
        alert(data['msg']);
    }
}
//清除登录按钮
function clear_login() {
    login_yesOrNo();
    var cllg = confirm("确认清除登录数据");
    if(cllg){
        document.getElementById("top_div_1").style.display="";
        ajaxRequest("GET",url+"/employee/clearLogin",clear_login_handle,"text","");
    }
}
function clear_login_handle(data) {
    var data = eval("("+data+")");
    if (data['code']==0){
        alert(data['msg']);
        document.getElementById("top_div_1").style.display="none";
        location.href=url+"/page/login";
    }else{
        document.getElementById("top_div_1").style.display="none";
        alert(data['msg']);
    }
}
//确认修改密码
function determine_modify_password(id) {
    login_yesOrNo();
    var a0_variable = document.getElementById(id);
    var old_pwd=a0_variable.oldPassword.value;
    var new_pwd=a0_variable.newPassword.value;
    var again_pwd=a0_variable.againPassword.value;
    if(old_pwd.trim()=="" || new_pwd.trim()=="" || again_pwd.trim()==""){
        alert("密码不能为空");
    }else if(new_pwd!=again_pwd){
        alert("两次输入密码不一致");
    }else{
        document.getElementById("top_div_1").style.display="";
        var param="newPwd="+new_pwd+"@#$oldPwd="+old_pwd;
        ajaxRequest("POST",url+"/employee/modifyPwd",modify_pwd_handle,"text",param)
    }
}

function modify_pwd_handle(data) {
    var data = eval("("+data+")");
    if(data['code']==0){
        document.getElementById("top_div_1").style.display="none";
        alert("修改成功，请重新登录系统");
        location.href=url+"/page/login";
    }else{
        document.getElementById("top_div_1").style.display="none";
        alert(data['msg']);
    }
}


//设置菜所属的分类div
function setClassificationIdsToDiv(id) {
    switch (id){
        case 1:
            document.getElementById('a_div_id_66').style.display='';
            document.getElementById('b_div_id_66').style.display='none';
            break;
        case 2:
            document.getElementById('a_div_id_67').style.display='';
            document.getElementById('b_div_id_67').style.display='none';
            break;
        case 3:
            document.getElementById('a_div_id_68').style.display='';
            document.getElementById('b_div_id_68').style.display='none';
            break;
        case 4:
            document.getElementById('a_div_id_69').style.display='';
            document.getElementById('b_div_id_69').style.display='none';
            break;
    }
}
//点击已选择的
function choose_Classification(id) {
    document.getElementById('a'+id).style.display='none';
    document.getElementById('b'+id).style.display='';
}
//点击未选择的分类
function cancel_Classification(id) {
    document.getElementById('a'+id).style.display='';
    document.getElementById('b'+id).style.display='none';
}
function setInitialState() {
    document.getElementById('a_div_id_66').style.display='none';
    document.getElementById('b_div_id_66').style.display='';
    document.getElementById('a_div_id_67').style.display='none';
    document.getElementById('b_div_id_67').style.display='';
    document.getElementById('a_div_id_68').style.display='none';
    document.getElementById('b_div_id_68').style.display='';
    document.getElementById('a_div_id_69').style.display='none';
    document.getElementById('b_div_id_69').style.display='';
}

//遍历表格   并输出
/**
 * 遍历表格内容返回数组
 */
/*function getTableContent(id){
    var mytable = document.getElementById(id);
    var data = [];
    for(var i=0,rows=mytable.rows.length; i<rows; i++){
        for(var j=0,cells=mytable.rows[i].cells.length; j<cells; j++){
            if(!data[i]){
                data[i] = new Array();
            }
            data[i][j] = mytable.rows[i].cells[j].innerHTML;
        }
    }
    return data;
}*/

/**
 * 显示表格内容
 */
/*function showTableContent(id){
    var data = getTableContent(id);
    var tmp = '';
    for(i=0,rows=data.length; i<rows; i++){
        for(j=0,cells=data[i].length; j<cells; j++){
            tmp += data[i][j] + ',';
        }
        tmp += '<br>';
    }
    document.getElementById('result').innerHTML = tmp;
}*/