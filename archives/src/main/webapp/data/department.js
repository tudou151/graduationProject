//更新部门面板中的内容
function update_department_div(data) {
    document.getElementById('d_div_id_19').innerHTML=data;
    document.getElementById('top_div_1').style.display='none';
}

function close_department_content_panel(id) {
    document.getElementById('top_div_2').style.display='none';
    document.getElementById(id).style.display='none';
}
//部门上一页
function department_upPage(id,currentPageNum) {
    login_yesOrNo();
    document.getElementById('top_div_1').style.display='';
    //var currentPage = document.getElementById(id).value;
    currentPageNum--;
    ajaxRequest("GET",url+"/Department/getPage?currentPage="+currentPageNum,update_department_div,"text","");
}
//部门  下一页
function department_downPage(id,currentPageNum) {
    login_yesOrNo();
    document.getElementById('top_div_1').style.display='';
    //var currentPage = document.getElementById(id).value;
    ++currentPageNum;
    ajaxRequest("GET",url+"/Department/getPage?currentPage="+currentPageNum,update_department_div,"text","");
}
//部门 跳转到指定页面
function department_kipPage(id,pageNum,currentPageNum) {
    login_yesOrNo();
    var currentPage = document.getElementById(id).value;
    if(currentPage<=pageNum){
        ajaxRequest("GET",url+"/Department/getPage?currentPage="+currentPage,update_department_div,"text","");
    }else{
        alert("请输入在页数范围内的数字");
        document.getElementById('d_div_id_26').value=currentPageNum;
    }
}

var department_checkbox_id;
var department_select_YesOrNo=0;
//部门选中或取消全部复选框
function department_select_all(num) {
    var current_num=1;
    if (department_select_YesOrNo==0){
        while(current_num<=num){
            department_checkbox_id = "b_checkbox_"+current_num;
            document.getElementById(department_checkbox_id).checked = true;
            current_num++;
        }
        department_select_YesOrNo=1;
    }else{
        while(current_num<=num){
            department_checkbox_id = "b_checkbox_"+current_num;
            document.getElementById(department_checkbox_id).checked = false;
            current_num++;
        }
        department_select_YesOrNo=0;
    }
}
//删除部门
function del_department(num) {
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
            ajaxRequest("POST",url+"/Department/deleteDepartment",del_department_handle,"text","ids="+ids.toString());
        }else{
            document.getElementById('top_div_1').style.display='none';
        }
    }else{
        document.getElementById('top_div_1').style.display='none';
        alert("请选择要删除的数据");
    }
}
//删除部门的结果处理
function del_department_handle(data) {
    login_yesOrNo();
    document.getElementById('top_div_1').style.display='';
    data=eval("("+data+")");
    if (data['code']==0){
        alert("删除成功");
        ajaxRequest("GET",url+"/Department/getAllDepartments",update_department_div,"text","");
    }else{
        alert(data['msg']);
    }
}

//根据名称查询部门
function selectDepartment_byDepartmentName(inputId) {
    login_yesOrNo();
    var input = document.getElementById(inputId);
    var departmentName = input.departmentName.value;
    var param="departmentName="+departmentName;
    ajaxRequest("POST",url+"/Department/getDepartmentNumberByDepartmentName",select_department_handle,"text",param);
}

function select_department_handle(data) {
    login_yesOrNo();
    document.getElementById('top_div_2').style.display="none";
    var data = eval("("+data+")");
    if (data['code']==0) {
        var departmentNumber = data['data'];
        ajaxRequest("GET",url+"/Department/getDepartmentByDepartmentNumber?departmentNumber="+departmentNumber,update_department_div,"text","");
    }else{
        document.getElementById('top_div_1').style.display="none";
        alert(data['msg']);
    }
}
//新增部门
function add_department(id) {
    document.getElementById('top_div_2').style.display='';
    document.getElementById(id).style.display='';
}

//确定部门
function add_department_true(formId) {

    login_yesOrNo();
    var form = document.getElementById(formId);
    var departmentNumber = form.departmentNumber.value;
    var departmentName = form.departmentName.value;
    var departmentHead = form.departmentHead.value;
    var departmentAddress = form.departmentAddress.value;
    var departmentTel = form.departmentTel.value;
    var departmentFax = form.departmentFax.value;
    if (departmentNumber=="" || departmentName=="" || departmentHead=="" || departmentAddress=="" || departmentFax=="" || departmentTel==""){
        alert("请将信息填写完整");
    }else{
        var param="departmentNumber="+departmentNumber+"@#$departmentName="+departmentName+"@#$departmentHead="+departmentHead+"@#$departmentAddress="+departmentAddress+"@#$departmentTel="+departmentTel+"@#$departmentFax="+departmentFax;
        ajaxRequest("POST",url+"/Department/addDepartment",add_department_handle,"text",param);
    }
}
//部门新增结果处理
function add_department_handle(data) {
    login_yesOrNo();
    document.getElementById('top_div_2').style.display='none';
    document.getElementById('d_div_id_22').style.display='none';
    var data= eval("("+data+")");
    if(data['code']==0){
        alert(data['msg']);
        ajaxRequest("GET",url+"/page/returnDepartmentJSP",update_department_div,"text","");
    }else{
        alert(data['msg']);
    }
}
//选择要修改的部门
function modify_department_btn(num) {
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
        ajaxRequest("GET",url+"/Department/getDepartmentById?id="+id,show_modify_department,"text","");
    }else{
        document.getElementById('top_div_1').style.display="none";
        alert("请选择一条数据进行修改");
    }
}
var modify_department_id;
//展示要修改的部门信息
function show_modify_department(data) {
    document.getElementById('top_div_1').style.display="none";
    var data = eval("("+data+")");
    if (data['code']==0){
        document.getElementById('top_div_2').style.display="";
        document.getElementById('d_div_id_25').style.display="";
        var datas=data['data'];
        modify_department_id=datas['id'];
        var forms=document.getElementById('d_div_id_43');
        forms.departmentNumber.value=datas['departmentNumber'];
        forms.departmentName.value=datas['departmentName'];
        forms.departmentHead.value=datas['departmentHead'];
        forms.departmentAddress.value=datas['departmentAddress'];
        forms.departmentTel.value=datas['departmentTel'];
        forms.departmentFax.value=datas['departmentFax'];
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

function  update_department_true(id) {
    login_yesOrNo();
    document.getElementById('top_div_2').style.display="none";
    document.getElementById('d_div_id_25').style.display="none";
    document.getElementById('top_div_1').style.display="";
    var form=document.getElementById(id);
    var departmentNumber = form.departmentNumber.value;
    var departmentName = form.departmentName.value;
    var departmentHead = form.departmentHead.value;
    var departmentAddress = form.departmentAddress.value;
    var departmentTel = form.departmentTel.value;
    var departmentFax = form.departmentFax.value;

    var param="departmentNumber="+departmentNumber+"@#$departmentName="+departmentName+"@#$departmentHead="+departmentHead+"@#$departmentAddress="+departmentAddress+"@#$departmentTel="+departmentTel+"@#$departmentFax="+departmentFax+"@#$id="+modify_department_id;

    ajaxRequest("POST",url+"/Department/modifyDepartment",update_department_handle,"text",param);
}
//修改部门的处理结果
function update_department_handle(data) {
    var data=eval("("+data+")");
    if(data['code']==0){
        document.getElementById('top_div_1').style.display="none";
        alert("修改成功");
        login_yesOrNo();
        ajaxRequest("GET",url+"/page/returnDepartmentJSP",update_department_div,"text","");
    }else{
        document.getElementById('top_div_1').style.display="none";
        alert(data['msg']);
    }
}