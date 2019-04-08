//更新工作经历面板中的内容
function update_experience_div(data) {
    document.getElementById('i_div_id_19').innerHTML=data;
    document.getElementById('top_div_1').style.display='none';
}

function close_experience_content_panel(id) {
    document.getElementById('top_div_2').style.display='none';
    document.getElementById(id).style.display='none';
}
//工作经历上一页
function experience_upPage(id,currentPageNum) {
    login_yesOrNo();
    document.getElementById('top_div_1').style.display='';
    //var currentPage = document.getElementById(id).value;
    currentPageNum--;
    ajaxRequest("GET",url+"/Experience/getPage?currentPage="+currentPageNum,update_experience_div,"text","");
}
//工作经历  下一页
function experience_downPage(id,currentPageNum) {
    login_yesOrNo();
    document.getElementById('top_div_1').style.display='';
    //var currentPage = document.getElementById(id).value;
    ++currentPageNum;
    ajaxRequest("GET",url+"/Experience/getPage?currentPage="+currentPageNum,update_experience_div,"text","");
}
//工作经历 跳转到指定页面
function experience_kipPage(id,pageNum,currentPageNum) {
    login_yesOrNo();
    var currentPage = document.getElementById(id).value;
    if(currentPage<=pageNum){
        ajaxRequest("GET",url+"/Experience/getPage?currentPage="+currentPage,update_experience_div,"text","");
    }else{
        alert("请输入在页数范围内的数字");
        document.getElementById('i_div_id_26').value=currentPageNum;
    }
}

var experience_checkbox_id;
var experience_select_YesOrNo=0;
//工作经历选中或取消全部复选框
function experience_select_all(num) {
    var current_num=1;
    if (experience_select_YesOrNo==0){
        while(current_num<=num){
            experience_checkbox_id = "b_checkbox_"+current_num;
            document.getElementById(experience_checkbox_id).checked = true;
            current_num++;
        }
        experience_select_YesOrNo=1;
    }else{
        while(current_num<=num){
            experience_checkbox_id = "b_checkbox_"+current_num;
            document.getElementById(experience_checkbox_id).checked = false;
            current_num++;
        }
        experience_select_YesOrNo=0;
    }
}
//删除工作经历
function del_experience(num) {
    login_yesOrNo();
    document.getElementById('top_div_1').style.display='';
    var ids = new Array();
    var ids_num=1;
    var add_String_Array=0;
    while(ids_num<=num){
        checkbox_id = "b_checkbox_"+ids_num;
        if (document.getElementById(checkbox_id).checked){
            ids[add_String_Array]=""+document.getElementById(checkbox_id).value;
            add_String_Array++;
        }
        ids_num++;
    }

    if(ids.length!=0){
        if(confirm("确定删除？")){
            ajaxRequest("POST",url+"/Experience/deleteExperience",del_experience_handle,"text","ids="+ids.toString());
        }else{
            document.getElementById('top_div_1').style.display='none';
        }
    }else{
        document.getElementById('top_div_1').style.display='none';
        alert("请选择要删除的数据");
    }
}
//删除工作经历的结果处理
function del_experience_handle(data) {
    login_yesOrNo();
    document.getElementById('top_div_1').style.display='';
    data=eval("("+data+")");
    if (data['code']==0){
        alert("删除成功");
        ajaxRequest("GET",url+"/Experience/getAllExperiences",update_experience_div,"text","");
    }else{
        alert(data['msg']);
    }
}
//根据姓名查询
function selectExperience_byemployeename(inputId) {
    login_yesOrNo();
    var input = document.getElementById(inputId);
    var employeeName = input.employeeName.value;
    var param="employeeName="+employeeName;
    ajaxRequest("POST",url+"/Experience/getEmployeeNumberByEmployeeName",select_experience_handle,"text",param);
}

function select_experience_handle(data) {
    login_yesOrNo();
    document.getElementById('top_div_2').style.display="none";
    var data = eval("("+data+")");
    if (data['code']==0) {
        var employeeNumber = data['data'];
        ajaxRequest("GET",url+"/Experience/getExperienceByEmployeeNumber?employeeNumber="+employeeNumber,update_experience_div,"text","");
    }else{
        document.getElementById('top_div_1').style.display="none";
        alert(data['msg']);
    }
}

//新增工作经历
function add_experience(id) {
    document.getElementById('top_div_2').style.display='';
    document.getElementById(id).style.display='';
}

//确定工作经历
function add_experience_true(formId) {

    login_yesOrNo();
    var form = document.getElementById(formId);
    var unit = form.unit.value;
    var post = form.post.value;
    var endTime = form.endTime.value;
    var beginTime = form.beginTime.value;
    var reason = form.reason.value;
    var ticket = form.ticket.value;
    var employeeNumber = form.employeeNumber.value;
    if (unit=="" || post=="" || endTime=="" || beginTime=="" || ticket=="" || reason=="" ||employeeNumber==""){
        alert("请将信息填写完整");
    }else{
        var param="unit="+unit+"@#$post="+post+"@#$endTime="+endTime+"@#$employeeNumber="+employeeNumber+"@#$beginTime="+beginTime+"@#$reason="+reason+"@#$ticket="+ticket;
        ajaxRequest("POST",url+"/Experience/addExperience",add_experience_handle,"text",param);
    }
}
//工作经历新增结果处理
function add_experience_handle(data) {
    login_yesOrNo();
    document.getElementById('top_div_2').style.display='none';
    document.getElementById('i_div_id_22').style.display='none';
    var data= eval("("+data+")");
    if(data['code']==0){
        alert(data['msg']);
        ajaxRequest("GET",url+"/page/returnExperienceJSP",update_experience_div,"text","");
    }else{
        alert(data['msg']);
    }
}
//选择要修改的工作经历
function modify_experience_btn(num) {
    login_yesOrNo();
    document.getElementById('top_div_1').style.display="";
    var ids_num1=1;
    var YN_greater_one=0;
    var id;
    while (ids_num1<=num){
        checkbox_id = "b_checkbox_"+ids_num1;
        if (document.getElementById(checkbox_id).checked){
            if (YN_greater_one==0){
                id = document.getElementById(checkbox_id).value;
                YN_greater_one++;
            }else{
                YN_greater_one=-1;
                break;
            }
        }
        ids_num1++;
    }
    if (YN_greater_one==1){
        ajaxRequest("GET",url+"/Experience/getExperienceById?id="+id,show_modify_experience,"text","");
    }else{
        document.getElementById('top_div_1').style.display="none";
        alert("请选择一条数据进行修改");
    }
}
var modify_experience_id;
//展示要修改的工作经历信息
function show_modify_experience(data) {
    document.getElementById('top_div_1').style.display="none";
    var data = eval("("+data+")");
    if (data['code']==0){
        document.getElementById('top_div_2').style.display="";
        document.getElementById('i_div_id_25').style.display="";
        var datas=data['data'];
        modify_experience_id=datas['id'];
        var forms=document.getElementById('i_div_id_43');
        forms.unit.value=datas['unit'];
        forms.post.value=datas['post'];
        forms.endTime.value=datas['endTime'];
        forms.beginTime.value=datas['beginTime'];
        forms.reason.value=datas['reason'];
        forms.ticket.value=datas['ticket'];
        forms.employeeNumber.value = datas['employeeNumber'];
    }else{
        alert(data['msg']);
    }
}
//时间转换
function date_conversion(time) {
    var date = new Date(time);
    var str = date.getFullYear() + "-" + fix((date.getMonth() + 1),2) + "-" + fix(date.getDate(),2) + "T" + fix(date.getHours(),2) + ":" + fix(date.getMinutes(),2);
    return str;
}
function fix(num, length) {
    return ('' + num).length < length ? ((new Array(length + 1)).join('0') + num).slice(-length) : '' + num;
}

function  update_experience_true(id) {
    login_yesOrNo();
    document.getElementById('top_div_2').style.display="none";
    document.getElementById('i_div_id_25').style.display="none";
    document.getElementById('top_div_1').style.display="";
    var form=document.getElementById(id);
    var unit = form.unit.value;
    var post = form.post.value;
    var beginTime = form.beginTime.value;
    var endTime = form.endTime.value;
    var reason = form.reason.value;
    var ticket = form.ticket.value;
    var employeeNumber = form.employeeNumber.value;

    var param="unit="+unit+"@#$employeeNumber="+employeeNumber+"@#$post="+post+"@#$beginTime="+beginTime+"@#$endTime="+endTime+"@#$reason="+reason+"@#$ticket="+ticket+"@#$id="+modify_experience_id;

    ajaxRequest("POST",url+"/Experience/modifyExperience",update_experience_handle,"text",param);
}
//修改工作经历的处理结果
function update_experience_handle(data) {
    var data=eval("("+data+")");
    if(data['code']==0){
        document.getElementById('top_div_1').style.display="none";
        alert("修改成功");
        login_yesOrNo();
        ajaxRequest("GET",url+"/page/returnExperienceJSP",update_experience_div,"text","");
    }else{
        document.getElementById('top_div_1').style.display="none";
        alert(data['msg']);
    }
}