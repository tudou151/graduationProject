//更新奖惩信息面板中的内容
function update_awardAndPunish_div(data) {
    document.getElementById('c_div_id_19').innerHTML=data;
    document.getElementById('top_div_1').style.display='none';
}

function close_awardAndPunish_content_panel(id) {
    document.getElementById('top_div_2').style.display='none';
    document.getElementById(id).style.display='none';
}
//奖惩信息上一页
function awardAndPunish_upPage(id,currentPageNum) {
    login_yesOrNo();
    document.getElementById('top_div_1').style.display='';
    //var currentPage = document.getElementById(id).value;
    currentPageNum--;
    ajaxRequest("GET",url+"/AwardAndPunish/getPage?currentPage="+currentPageNum,update_awardAndPunish_div,"text","");
}
//奖惩信息  下一页
function awardAndPunish_downPage(id,currentPageNum) {
    login_yesOrNo();
    document.getElementById('top_div_1').style.display='';
    //var currentPage = document.getElementById(id).value;
    ++currentPageNum;
    ajaxRequest("GET",url+"/AwardAndPunish/getPage?currentPage="+currentPageNum,update_awardAndPunish_div,"text","");
}
//奖惩信息 跳转到指定页面
function awardAndPunish_kipPage(id,pageNum,currentPageNum) {
    login_yesOrNo();
    var currentPage = document.getElementById(id).value;
    if(currentPage<=pageNum){
        ajaxRequest("GET",url+"/AwardAndPunish/getPage?currentPage="+currentPage,update_awardAndPunish_div,"text","");
    }else{
        alert("请输入在页数范围内的数字");
        document.getElementById('c_div_id_26').value=currentPageNum;
    }
}

var awardAndPunish_checkbox_id;
var awardAndPunish_select_YesOrNo=0;
//奖惩信息选中或取消全部复选框
function awardAndPunish_select_all(num) {
    var current_num=1;
    if (awardAndPunish_select_YesOrNo==0){
        while(current_num<=num){
            awardAndPunish_checkbox_id = "b_checkbox_"+current_num;
            document.getElementById(awardAndPunish_checkbox_id).checked = true;
            current_num++;
        }
        awardAndPunish_select_YesOrNo=1;
    }else{
        while(current_num<=num){
            awardAndPunish_checkbox_id = "b_checkbox_"+current_num;
            document.getElementById(awardAndPunish_checkbox_id).checked = false;
            current_num++;
        }
        awardAndPunish_select_YesOrNo=0;
    }
}
//删除奖惩信息
function del_awardAndPunish(num) {
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
            ajaxRequest("POST",url+"/AwardAndPunish/deleteAwardAndPunish",del_awardAndPunish_handle,"text","ids="+ids.toString());
        }else{
            document.getElementById('top_div_1').style.display='none';
        }
    }else{
        document.getElementById('top_div_1').style.display='none';
        alert("请选择要删除的数据");
    }
}
//删除奖惩信息的结果处理
function del_awardAndPunish_handle(data) {
    login_yesOrNo();
    document.getElementById('top_div_1').style.display='';
    data=eval("("+data+")");
    if (data['code']==0){
        alert("删除成功");
        ajaxRequest("GET",url+"/AwardAndPunish/getAllAwardAndPunishs",update_awardAndPunish_div,"text","");
    }else{
        alert(data['msg']);
    }
}
//新增奖惩信息
function add_awardAndPunish(id) {
    document.getElementById('top_div_2').style.display='';
    document.getElementById(id).style.display='';
}

//确定奖惩信息
function add_awardAndPunish_true(formId) {

    login_yesOrNo();
    var form = document.getElementById(formId);
    var genre = form.genre.value;
    var company = form.company.value;
    var reason = form.reason.value;
    var happenTime = form.happenTime.value;
    var employeeNumber=form.employeeNumber.value;

    if (genre=="" || company=="" || reason=="" || happenTime=="" || employeeNumber==""){
        alert("请将信息填写完整");
    }else{
        var param="genre="+genre+"@#$company="+company+"@#$reason="+reason+"@#$happenTime="+happenTime + "@#$employeeNumber=" + employeeNumber;
        ajaxRequest("POST",url+"/AwardAndPunish/addAwardAndPunish",add_awardAndPunish_handle,"text",param);
    }
}
//奖惩信息新增结果处理
function add_awardAndPunish_handle(data) {
    login_yesOrNo();
    document.getElementById('top_div_2').style.display='none';
    document.getElementById('c_div_id_22').style.display='none';
    var data= eval("("+data+")");
    if(data['code']==0){
        alert(data['msg']);
        ajaxRequest("GET",url+"/page/returnAwardAndPunishJSP",update_awardAndPunish_div,"text","");
    }else{
        alert(data['msg']);
    }
}

//根据姓名查询
function select_awardAndPunish_byemployeename(inputId) {
    login_yesOrNo();
    var input = document.getElementById(inputId);
    var employeeName = input.employeeName.value;
    var param="employeeName="+employeeName;
    ajaxRequest("POST",url+"/AwardAndPunish/getEmployeeNumberByEmployeeName",select_awardAndPunish_handle,"text",param);
}

function select_awardAndPunish_handle(data) {
    login_yesOrNo();
    document.getElementById('top_div_2').style.display="none";
    var data = eval("("+data+")");
    if (data['code']==0) {
        var employeeNumber = data['data'];
        ajaxRequest("GET",url+"/AwardAndPunish/getAwardAndPunishByEmployeeNumber?employeeNumber="+employeeNumber,update_awardAndPunish_div,"text","");
    }else{
        document.getElementById('top_div_1').style.display="none";
        alert(data['msg']);
    }
}
//选择要修改的奖惩信息
function modify_awardAndPunish_btn(num) {
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
        ajaxRequest("GET",url+"/AwardAndPunish/getAwardAndPunishById?id="+id,show_modify_awardAndPunish,"text","");
    }else{
        document.getElementById('top_div_1').style.display="none";
        alert("请选择一条数据进行修改");
    }
}

var modify_awardAndPunish_id;
//展示要修改的奖惩信息信息
function show_modify_awardAndPunish(data) {
    document.getElementById('top_div_1').style.display="none";
    var data = eval("("+data+")");
    if (data['code']==0){
        document.getElementById('top_div_2').style.display="";
        document.getElementById('c_div_id_25').style.display="";
        var datas=data['data'];
        modify_awardAndPunish_id=datas['id'];
        var forms=document.getElementById('c_div_id_43');
        forms.genre.value=datas['genre'];
        forms.company.value=datas['company'];
        forms.reason.value=datas['reason'];
        forms.happenTime.value=datas['happenTime'];
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

function  update_awardAndPunish_true(id) {
    login_yesOrNo();
    document.getElementById('top_div_2').style.display="none";
    document.getElementById('c_div_id_25').style.display="none";
    document.getElementById('top_div_1').style.display="";
    var form=document.getElementById(id);
    var genre = form.genre.value;
    var company = form.company.value;
    var reason = form.reason.value;
    var happenTime = form.happenTime.value;
    var employeeNumber = form.employeeNumber.value;

    var param="genre="+genre+"@#$company="+company+"@#$reason="+reason+"@#$happenTime="+happenTime+"@#$employeeNumber="+employeeNumber+"@#$id="+modify_awardAndPunish_id;

    ajaxRequest("POST",url+"/AwardAndPunish/modifyAwardAndPunish",update_awardAndPunish_handle,"text",param);
}
//修改奖惩信息的处理结果
function update_awardAndPunish_handle(data) {
    var data=eval("("+data+")");
    if(data['code']==0){
        document.getElementById('top_div_1').style.display="none";
        alert("修改成功");
        login_yesOrNo();
        ajaxRequest("GET",url+"/page/returnAwardAndPunishJSP",update_awardAndPunish_div,"text","");
    }else{
        document.getElementById('top_div_1').style.display="none";
        alert(data['msg']);
    }
}