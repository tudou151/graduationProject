//更新学历面板中的内容
function update_education_div(data) {
    document.getElementById('e_div_id_19').innerHTML=data;
    document.getElementById('top_div_1').style.display='none';
}

function close_education_content_panel(id) {
    document.getElementById('top_div_2').style.display='none';
    document.getElementById(id).style.display='none';
}
//学历上一页
function education_upPage(id,currentPageNum) {
    login_yesOrNo();
    document.getElementById('top_div_1').style.display='';
    //var currentPage = document.getElementById(id).value;
    currentPageNum--;
    ajaxRequest("GET",url+"/Education/getPage?currentPage="+currentPageNum,update_education_div,"text","");
}
//学历  下一页
function education_downPage(id,currentPageNum) {
    login_yesOrNo();
    document.getElementById('top_div_1').style.display='';
    //var currentPage = document.getElementById(id).value;
    ++currentPageNum;
    ajaxRequest("GET",url+"/Education/getPage?currentPage="+currentPageNum,update_education_div,"text","");
}
//学历 跳转到指定页面
function education_kipPage(id,pageNum,currentPageNum) {
    login_yesOrNo();
    var currentPage = document.getElementById(id).value;
    if(currentPage<=pageNum){
        ajaxRequest("GET",url+"/Education/getPage?currentPage="+currentPage,update_education_div,"text","");
    }else{
        alert("请输入在页数范围内的数字");
        document.getElementById('e_div_id_26').value=currentPageNum;
    }
}

var education_checkbox_id;
var education_select_YesOrNo=0;
//学历选中或取消全部复选框
function education_select_all(num) {
    var current_num=1;
    if (education_select_YesOrNo==0){
        while(current_num<=num){
            education_checkbox_id = "b_checkbox_"+current_num;
            document.getElementById(education_checkbox_id).checked = true;
            current_num++;
        }
        education_select_YesOrNo=1;
    }else{
        while(current_num<=num){
            education_checkbox_id = "b_checkbox_"+current_num;
            document.getElementById(education_checkbox_id).checked = false;
            current_num++;
        }
        education_select_YesOrNo=0;
    }
}
//删除学历
function del_education(num) {
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
            ajaxRequest("POST",url+"/Education/deleteEducation",del_education_handle,"text","ids="+ids.toString());
        }else{
            document.getElementById('top_div_1').style.display='none';
        }
    }else{
        document.getElementById('top_div_1').style.display='none';
        alert("请选择要删除的数据");
    }
}
//删除学历的结果处理
function del_education_handle(data) {
    login_yesOrNo();
    document.getElementById('top_div_1').style.display='';
    data=eval("("+data+")");
    if (data['code']==0){
        alert("删除成功");
        ajaxRequest("GET",url+"/Education/getAllEducations",update_education_div,"text","");
    }else{
        alert(data['msg']);
    }
}

//根据姓名查询
function selectselectEducation_byemployeename(inputId) {
    login_yesOrNo();
    var input = document.getElementById(inputId);
    var employeeName = input.employeeName.value;
    var param="employeeName="+employeeName;
    ajaxRequest("POST",url+"/Education/getEmployeeNumberByEmployeeName",select_education_handle,"text",param);
}

function select_education_handle(data) {
    login_yesOrNo();
    document.getElementById('top_div_2').style.display="none";
    var data = eval("("+data+")");
    if (data['code']==0) {
        var employeeNumber = data['data'];
        ajaxRequest("GET",url+"/Education/getEducationByEmployeeNumber?employeeNumber="+employeeNumber,update_education_div,"text","");
    }else{
        document.getElementById('top_div_1').style.display="none";
        alert(data['msg']);
    }
}
//新增学历
function add_education(id) {
    document.getElementById('top_div_2').style.display='';
    document.getElementById(id).style.display='';
}

//确定学历
function add_education_true(formId) {

    login_yesOrNo();
    var form = document.getElementById(formId);
    var educationNumber = form.educationNumber.value;
    var graduation = form.graduation.value;
    var major = form.major.value;
    var genre = form.genre.value;
    var arrangement = form.arrangement.value;
    var beginTime = form.beginTime.value;
    var endTime = form.endTime.value;
    var status = form.status.value;
    var employeeNumber = form.employeeNumber.value;
    if (employeeNumber=="" || educationNumber=="" || graduation=="" || status=="" || beginTime=="" || endTime=="" || arrangement=="" || genre=="" || major==""){
        alert("请将信息填写完整");
    }else{
        var param="educationNumber="+educationNumber+"@#$employeeNumber="+employeeNumber+"@#$graduation="+graduation+"@#$major="+major+"@#$genre="+genre+"@#$arrangement="+arrangement+"@#$beginTime="+beginTime+"@#$endTime="+endTime+"@#$status="+status;
        ajaxRequest("POST",url+"/Education/addEducation",add_education_handle,"text",param);
    }
}
//学历新增结果处理
function add_education_handle(data) {
    login_yesOrNo();
    document.getElementById('top_div_2').style.display='none';
    document.getElementById('e_div_id_22').style.display='none';
    var data= eval("("+data+")");
    if(data['code']==0){
        alert(data['msg']);
        ajaxRequest("GET",url+"/page/returnEducationJSP",update_education_div,"text","");
    }else{
        alert(data['msg']);
    }
}
//选择要修改的学历
function modify_education_btn(num) {
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
        ajaxRequest("GET",url+"/Education/getEducationById?id="+id,show_modify_education,"text","");
    }else{
        document.getElementById('top_div_1').style.display="none";
        alert("请选择一条数据进行修改");
    }
}

var modify_education_id;
//展示要修改的学历信息
function show_modify_education(data) {
    document.getElementById('top_div_1').style.display="none";
    var data = eval("("+data+")");

    if (data['code'] == 0){
        document.getElementById('top_div_2').style.display="";
        document.getElementById('e_div_id_25').style.display="";
        var datas=data['data'];
        modify_education_id=datas['id'];
        var forms=document.getElementById('e_div_id_43');
        forms.educationNumber.value=datas['educationNumber'];
        forms.graduation.value=datas['graduation'];
        forms.major.value=datas['major'];
        forms.genre.value=datas['genre'];
        forms.arrangement.value=datas['arrangement'];
        forms.beginTime.value=datas['beginTime'];
        forms.endTime.value=datas['endTime'];
        forms.status.value=datas['status'];
        forms.employeeNumber.value = datas['employeeNumber'];
    }else{
        alert(data['msg'])
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

function  update_education_true(id) {
    login_yesOrNo();
    document.getElementById('top_div_2').style.display="none";
    document.getElementById('e_div_id_25').style.display="none";
    document.getElementById('top_div_1').style.display="";
    var form=document.getElementById(id);
    var educationNumber = form.educationNumber.value;
    var graduation = form.graduation.value;
    var major = form.major.value;
    var genre = form.genre.value;
    var arrangement = form.arrangement.value;
    var beginTime = form.beginTime.value;
    var endTime = form.endTime.value;
    var status = form.status.value;
    var employeeNumber = form.employeeNumber.value;

    var param="educationNumber="+educationNumber+"@#$employeeNumber="+employeeNumber+"@#$graduation="+graduation+"@#$major="+major+"@#$genre="+genre+"@#$arrangement="+arrangement+"@#$beginTime="+beginTime+"@#$endTime="+endTime+"@#$status="+status+"@#$id="+modify_education_id;
    // alert(modify_education_id + "  :modify_education_id");

    ajaxRequest("POST",url+"/Education/modifyEducation",update_education_handle,"text",param);

}
//修改学历的处理结果
function update_education_handle(data) {
    var data=eval("("+data+")");
    if(data['code']==0){
        document.getElementById('top_div_1').style.display="none";
        alert("修改成功");
        login_yesOrNo();
        ajaxRequest("GET",url+"/page/returnEducationJSP",update_education_div,"text","");
    }else{
        document.getElementById('top_div_1').style.display="none";
        alert(data['msg']);
    }
}