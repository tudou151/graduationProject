//更新职称面板中的内容
function update_title_div(data) {
    document.getElementById('t_div_id_19').innerHTML=data;
    document.getElementById('top_div_1').style.display='none';
}

function close_title_content_panel(id) {
    document.getElementById('top_div_2').style.display='none';
    document.getElementById(id).style.display='none';
}
//职称上一页
function title_upPage(id,currentPageNum) {
    login_yesOrNo();
    document.getElementById('top_div_1').style.display='';
    //var currentPage = document.getElementById(id).value;
    currentPageNum--;
    ajaxRequest("GET",url+"/Title/getPage?currentPage="+currentPageNum,update_title_div,"text","");
}
//职称  下一页
function title_downPage(id,currentPageNum) {
    login_yesOrNo();
    document.getElementById('top_div_1').style.display='';
    //var currentPage = document.getElementById(id).value;
    ++currentPageNum;
    ajaxRequest("GET",url+"/Title/getPage?currentPage="+currentPageNum,update_title_div,"text","");
}
//职称 跳转到指定页面
function title_kipPage(id,pageNum,currentPageNum) {
    login_yesOrNo();
    var currentPage = document.getElementById(id).value;
    if(currentPage<=pageNum){
        ajaxRequest("GET",url+"/Title/getPage?currentPage="+currentPage,update_title_div,"text","");
    }else{
        alert("请输入在页数范围内的数字");
        document.getElementById('t_div_id_26').value=currentPageNum;
    }
}

var title_checkbox_id;
var title_select_YesOrNo=0;
//职称选中或取消全部复选框
function title_select_all(num) {
    var current_num=1;
    if (title_select_YesOrNo==0){
        while(current_num<=num){
            title_checkbox_id = "b_checkbox_"+current_num;
            document.getElementById(title_checkbox_id).checked = true;
            current_num++;
        }
        title_select_YesOrNo=1;
    }else{
        while(current_num<=num){
            title_checkbox_id = "b_checkbox_"+current_num;
            document.getElementById(title_checkbox_id).checked = false;
            current_num++;
        }
        title_select_YesOrNo=0;
    }
}
//删除职称
function del_title(num) {
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
            ajaxRequest("POST",url+"/Title/deleteTitle",del_title_handle,"text","ids="+ids.toString());
        }else{
            document.getElementById('top_div_1').style.display='none';
        }
    }else{
        document.getElementById('top_div_1').style.display='none';
        alert("请选择要删除的数据");
    }
}
//删除职称的结果处理
function del_title_handle(data) {
    login_yesOrNo();
    document.getElementById('top_div_1').style.display='';
    data=eval("("+data+")");
    if (data['code']==0){
        alert("删除成功");
        ajaxRequest("GET",url+"/Title/getAllTitles",update_title_div,"text","");
    }else{
        alert(data['msg']);
    }
}

//根据姓名查询
function selectTitle_byemployeename(inputId) {
    login_yesOrNo();
    var input = document.getElementById(inputId);
    var employeeName = input.employeeName.value;
    var param="employeeName="+employeeName;
    ajaxRequest("POST",url+"/Title/getEmployeeNumberByEmployeeName",select_title_handle,"text",param);
}

function select_title_handle(data) {
    login_yesOrNo();
    document.getElementById('top_div_2').style.display="none";
    var data = eval("("+data+")");
    if (data['code']==0) {
        var employeeNumber = data['data'];
        ajaxRequest("GET",url+"/Title/getTitleByEmployeeNumber?employeeNumber="+employeeNumber,update_title_div,"text","");
    }else{
        document.getElementById('top_div_1').style.display="none";
        alert(data['msg']);
    }
}
//新增职称
function add_title(id) {
    document.getElementById('top_div_2').style.display='';
    document.getElementById(id).style.display='';
}

//确定职称
function add_title_true(formId) {

    login_yesOrNo();
    var form = document.getElementById(formId);
    var titleNumber = form.titleNumber.value;
    var titleName = form.titleName.value;
    var happenTime = form.happenTime.value;
    var organization = form.organization.value;
    var employeeNumber = form.employeeNumber.value;
    if (titleNumber=="" || titleName=="" || happenTime=="" || organization=="" || employeeNumber=="" ){
        alert("请将信息填写完整");
    }else{
        var param="titleNumber="+titleNumber+"@#$titleName="+titleName+"@#$employeeNumber="+employeeNumber+"@#$happenTime="+happenTime+"@#$organization="+organization;
        ajaxRequest("POST",url+"/Title/addTitle",add_title_handle,"text",param);
    }
}
//职称新增结果处理
function add_title_handle(data) {
    login_yesOrNo();
    document.getElementById('top_div_2').style.display='none';
    document.getElementById('t_div_id_22').style.display='none';
    var data= eval("("+data+")");
    if(data['code']==0){
        alert(data['msg']);
        ajaxRequest("GET",url+"/page/returnTitleJSP",update_title_div,"text","");
    }else{
        alert(data['msg']);
    }
}
//选择要修改的职称
function modify_title_btn(num) {
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
        ajaxRequest("GET",url+"/Title/getTitleById?id="+id,show_modify_title,"text","");
    }else{
        document.getElementById('top_div_1').style.display="none";
        alert("请选择一条数据进行修改");
    }
}
var modify_title_id;
//展示要修改的职称信息
function show_modify_title(data) {
    document.getElementById('top_div_1').style.display="none";
    var data = eval("("+data+")");
    if (data['code']==0){
        document.getElementById('top_div_2').style.display="";
        document.getElementById('t_div_id_25').style.display="";
        var datas=data['data'];
        modify_title_id=datas['id'];
        var forms=document.getElementById('t_div_id_43');
        forms.titleNumber.value=datas['titleNumber'];
        forms.titleName.value=datas['titleName'];
        forms.happenTime.value=(datas['happenTime']);
        forms.organization.value=datas['organization'];
        forms.employeeNumber.value= datas['employeeNumber'];

    }else{
        alert(data['msg'])
    }
}
//时间转换
function date_conversion(time) {
    var array = [3];
     array = time.slice("-");
    var str = array[0] + "/" + array[1]+ "/" + array[2] ;/*+ "/" + date.getMonth() + "/" + date.getDate()*/;
    alert("src1 : " + str );
    return str;
}
function fix(num, length) {
    return ('' + num).length < length ? ((new Array(length + 1)).join('0') + num).slice(-length) : '' + num;
}

function  update_title_true(id) {
    login_yesOrNo();
    document.getElementById('top_div_2').style.display="none";
    document.getElementById('t_div_id_25').style.display="none";
    document.getElementById('top_div_1').style.display="";
    var form=document.getElementById(id);
    var titleNumber = form.titleNumber.value;
    var titleName = form.titleName.value;
    var happenTime = form.happenTime.value;
    var organization = form.organization.value;
    var employeeNumber= form.employeeNumber.value;

    var param="titleNumber="+titleNumber+"@#$employeeNumber="+employeeNumber+"@#$titleName="+titleName+"@#$happenTime="+happenTime+"@#$organization="+organization+"@#$id="+modify_title_id;

    ajaxRequest("POST",url+"/Title/modifyTitle",update_title_handle,"text",param);
}
//修改职称的处理结果
function update_title_handle(data) {
    var data=eval("("+data+")");
    if(data['code']==0){
        document.getElementById('top_div_1').style.display="none";
        alert("修改成功");
        login_yesOrNo();
        ajaxRequest("GET",url+"/page/returnTitleJSP",update_title_div,"text","");
    }else{
        document.getElementById('top_div_1').style.display="none";
        alert(data['msg']);
    }
}