//更新证书面板中的内容
function update_certificate_div(data) {
    document.getElementById('g_div_id_19').innerHTML=data;
    document.getElementById('top_div_1').style.display='none';
}

function close_certificate_content_panel(id) {
    document.getElementById('top_div_2').style.display='none';
    document.getElementById(id).style.display='none';
}
//证书上一页
function certificate_upPage(id,currentPageNum) {
    login_yesOrNo();
    document.getElementById('top_div_1').style.display='';
    //var currentPage = document.getElementById(id).value;
    currentPageNum--;
    ajaxRequest("GET",url+"/Certificate/getPage?currentPage="+currentPageNum,update_certificate_div,"text","");
}
//证书  下一页
function certificate_downPage(id,currentPageNum) {
    login_yesOrNo();
    document.getElementById('top_div_1').style.display='';
    //var currentPage = document.getElementById(id).value;
    ++currentPageNum;
    ajaxRequest("GET",url+"/Certificate/getPage?currentPage="+currentPageNum,update_certificate_div,"text","");
}
//证书 跳转到指定页面
function certificate_kipPage(id,pageNum,currentPageNum) {
    login_yesOrNo();
    var currentPage = document.getElementById(id).value;
    if(currentPage<=pageNum){
        ajaxRequest("GET",url+"/Certificate/getPage?currentPage="+currentPage,update_certificate_div,"text","");
    }else{
        alert("请输入在页数范围内的数字");
        document.getElementById('g_div_id_26').value=currentPageNum;
    }
}

var certificate_checkbox_id;
var certificate_select_YesOrNo=0;
//证书选中或取消全部复选框
function certificate_select_all(num) {
    var current_num=1;
    if (certificate_select_YesOrNo==0){
        while(current_num<=num){
            certificate_checkbox_id = "b_checkbox_"+current_num;
            document.getElementById(certificate_checkbox_id).checked = true;
            current_num++;
        }
        certificate_select_YesOrNo=1;
    }else{
        while(current_num<=num){
            certificate_checkbox_id = "b_checkbox_"+current_num;
            document.getElementById(certificate_checkbox_id).checked = false;
            current_num++;
        }
        certificate_select_YesOrNo=0;
    }
}
//删除证书
function del_certificate(num) {
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
            ajaxRequest("POST",url+"/Certificate/deleteCertificate",del_certificate_handle,"text","ids="+ids.toString());
        }else{
            document.getElementById('top_div_1').style.display='none';
        }
    }else{
        document.getElementById('top_div_1').style.display='none';
        alert("请选择要删除的数据");
    }
}
//删除证书的结果处理
function del_certificate_handle(data) {
    login_yesOrNo();
    document.getElementById('top_div_1').style.display='';
    data=eval("("+data+")");
    if (data['code']==0){
        alert("删除成功");
        ajaxRequest("GET",url+"/Certificate/getAllCertificates",update_certificate_div,"text","");
    }else{
        alert(data['msg']);
    }
}
//根据姓名查询
function select_Certificate_byemployeename(inputId) {
    login_yesOrNo();
    var input = document.getElementById(inputId);
    var employeeName = input.employeeName.value;
    var param="employeeName="+employeeName;
    ajaxRequest("POST",url+"/Certificate/getEmployeeNumberByEmployeeName",select_certificate_handle,"text",param);
}

function select_certificate_handle(data) {
    login_yesOrNo();
    document.getElementById('top_div_2').style.display="none";
    var data = eval("("+data+")");
    if (data['code']==0) {
        var employeeNumber = data['data'];
        ajaxRequest("GET",url+"/Certificate/getCertificateByEmployeeNumber?employeeNumber="+employeeNumber,update_certificate_div,"text","");
    }else{
        document.getElementById('top_div_1').style.display="none";
        alert(data['msg']);
    }
}
//新增证书
function add_certificate(id) {
    document.getElementById('top_div_2').style.display='';
    document.getElementById(id).style.display='';
}

//确定证书
function add_certificate_true(formId) {

    login_yesOrNo();
    var form = document.getElementById(formId);
    var certificateNumber = form.certificateNumber.value;
    var name = form.name.value;
    var happenTime = form.happenTime.value;
    var organization = form.organization.value;
    var employeeNumber=form.employeeNumber.value;
    if (certificateNumber=="" || name=="" || happenTime=="" || organization=="" || employeeNumber==""){
        alert("请将信息填写完整");
    }else{
        var param="certificateNumber="+certificateNumber+"@#$name="+name+"@#$happenTime="+happenTime+"@#$organization="+organization+"@#$employeeNumber="+employeeNumber;
        ajaxRequest("POST",url+"/Certificate/addCertificate",add_certificate_handle,"text",param);
    }
}
//证书新增结果处理
function add_certificate_handle(data) {
    login_yesOrNo();
    document.getElementById('top_div_2').style.display='none';
    document.getElementById('g_div_id_22').style.display='none';
    var data= eval("("+data+")");
    if(data['code']==0){
        alert(data['msg']);
        ajaxRequest("GET",url+"/page/returnCertificateJSP",update_certificate_div,"text","");
    }else{
        alert(data['msg']);
    }
}
//选择要修改的证书
function modify_certificate_btn(num) {
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
        ajaxRequest("GET",url+"/Certificate/getCertificateById?id="+id,show_modify_certificate,"text","");
    }else{
        document.getElementById('top_div_1').style.display="none";
        alert("请选择一条数据进行修改");
    }
}
var modify_certificate_id;
//展示要修改的证书信息
function show_modify_certificate(data) {
    document.getElementById('top_div_1').style.display="none";
    var data = eval("("+data+")");
    if (data['code']==0){
        document.getElementById('top_div_2').style.display="";
        document.getElementById('g_div_id_25').style.display="";
        var datas=data['data'];
        modify_certificate_id=datas['id'];
        var forms=document.getElementById('g_div_id_43');
        forms.certificateNumber.value=datas['certificateNumber'];
        forms.name.value=datas['name'];
        forms.happenTime.value=datas['happenTime'];
        forms.organization.value=datas['organization'];
        forms.employeeNumber.value=datas['employeeNumber'];

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

function  update_certificate_true(id) {
    login_yesOrNo();
    document.getElementById('top_div_2').style.display="none";
    document.getElementById('g_div_id_25').style.display="none";
    document.getElementById('top_div_1').style.display="";
    var form=document.getElementById(id);
    var certificateNumber = form.certificateNumber.value;
    var name = form.name.value;
    var happenTime = form.happenTime.value;
    var organization = form.organization.value;
    var employeeNumber = form.employeeNumber.value;

    var param="certificateNumber="+certificateNumber+"@#$name="+name+"@#$happenTime="+happenTime+"@#$organization="+organization+"@#$employeeNumber="+employeeNumber+"@#$id="+modify_certificate_id;

    ajaxRequest("POST",url+"/Certificate/modifyCertificate",update_certificate_handle,"text",param);
}
//修改证书的处理结果
function update_certificate_handle(data) {
    var data=eval("("+data+")");
    if(data['code']==0){
        document.getElementById('top_div_1').style.display="none";
        alert("修改成功");
        login_yesOrNo();
        ajaxRequest("GET",url+"/page/returnCertificateJSP",update_certificate_div,"text","");
    }else{
        document.getElementById('top_div_1').style.display="none";
        alert(data['msg']);
    }
}