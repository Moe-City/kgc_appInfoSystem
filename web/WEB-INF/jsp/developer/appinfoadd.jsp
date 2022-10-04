<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="common/header.jsp"%>
<div class="clearfix"></div>
<div class="row">
  <div class="col-md-12 col-sm-12 col-xs-12">
    <div class="x_panel">
      <div class="x_title">
        <h2>新增APP基础信息 <i class="fa fa-user"></i><small>${devUserSession.devName}</small></h2>
             <div class="clearfix"></div>
      </div>
      <div class="x_content">
	  <!-- <div class="item form-group">
               <label class="control-label col-md-3 col-sm-3 col-xs-12" ></label>
               <div class="col-md-6 col-sm-6 col-xs-12">
                 <form action="uploadlogo" class="dropzone" style="height:100px;">
                 </form>
            <div class="clearfix"></div>
         </div>
       </div> -->
           <div class="clearfix"></div>
        <form class="form-horizontal form-label-left" action="appinfoaddsave" method="post" enctype="multipart/form-data">
          <div class="item form-group">
            <label class="control-label col-md-3 col-sm-3 col-xs-12" for="name">软件名称 <span class="required">*</span>
            </label>
            <div class="col-md-6 col-sm-6 col-xs-12">
              <input id="softwareName" class="form-control col-md-7 col-xs-12" 
               data-validate-length-range="20" data-validate-words="1" name="softwareName"  required="required"
               placeholder="请输入软件名称" type="text">
            </div>
          </div>
          <div class="item form-group">
            <label class="control-label col-md-3 col-sm-3 col-xs-12" for="name">APK名称 <span class="required">*</span>
            </label>
            <div class="col-md-6 col-sm-6 col-xs-12">
              <input id="APKName" class="form-control col-md-7 col-xs-12" 
              	data-validate-length-range="20" data-validate-words="1" name="APKName"  onblur="checkApkName()" required="required"
              	placeholder="请输入APK名称" type="text">
            </div>
          </div>
          
          <div class="item form-group">
            <label class="control-label col-md-3 col-sm-3 col-xs-12" for="name">支持ROM <span class="required">*</span>
            </label>
            <div class="col-md-6 col-sm-6 col-xs-12">
              <input id="supportROM" class="form-control col-md-7 col-xs-12" name="supportROM" 
              	data-validate-length-range="20" data-validate-words="1"   required="required"
              	placeholder="请输入支持的ROM" type="text">
            </div>
          </div>
          <div class="item form-group">
            <label class="control-label col-md-3 col-sm-3 col-xs-12" for="name">界面语言 <span class="required">*</span>
            </label>
            <div class="col-md-6 col-sm-6 col-xs-12">
              <input id="interfaceLanguage" class="form-control col-md-7 col-xs-12" 
              data-validate-length-range="20" data-validate-words="1" name="interfaceLanguage"   required="required"
              placeholder="请输入软件支持的界面语言" type="text">
            </div>
          </div>
          <div class="item form-group">
            <label class="control-label col-md-3 col-sm-3 col-xs-12" for="number">软件大小 <span class="required">*</span>
            </label>
            <div class="col-md-6 col-sm-6 col-xs-12">
              <input type="number" id="softwareSize" name="softwareSize"   required="required" onkeyup="value=value.replace(/[^\d]/g,'')"
              data-validate-minmax="10,500"  placeholder="请输入软件大小，单位为Mb" class="form-control col-md-7 col-xs-12">
            </div>
          </div>
          
          <div class="item form-group">
            <label class="control-label col-md-3 col-sm-3 col-xs-12" for="number">下载次数 <span class="required">*</span>
            </label>
            <div class="col-md-6 col-sm-6 col-xs-12">
              <input type="number" id="downloads" name="downloads"   required="required"
              data-validate-minmax="10,500"  placeholder="请输入下载次数" class="form-control col-md-7 col-xs-12">
            </div>
          </div>
          
          <div class="item form-group">
            <label class="control-label col-md-3 col-sm-3 col-xs-12"  for="select">所属平台 <span class="required">*</span></label>
            <div class="col-md-6 col-sm-6 col-xs-12">
              <select name="flatformId" id="flatformId" class="form-control"   required="required">
                <c:if test="${flatFormList != null }">
                  <option value="" selected>--请选择--</option>
                  <c:forEach var="dataDictionary" items="${flatFormList}">
                    <option <c:if test="${dataDictionary.valueId == queryFlatformId }">selected="selected"</c:if>
                            value="${dataDictionary.valueId}">${dataDictionary.valueName}</option>
                  </c:forEach>
                </c:if>
              </select>
            </div>
          </div>
          
          <div class="item form-group">
            <label class="control-label col-md-3 col-sm-3 col-xs-12" for="select">一级分类 <span class="required">*</span></label>
            <div class="col-md-6 col-sm-6 col-xs-12">
              <select name="categoryLevel1" id="categoryLevel1" class="form-control"   required="required">
                <c:if test="${categoryLevel1List != null }">
                  <option value="" selected>--请选择--</option>
                  <c:forEach var="appCategory" items="${categoryLevel1List}">
                    <option <c:if test="${appCategory.id == queryCategoryLevel1}">selected="selected"</c:if>
                            value="${appCategory.id}">${appCategory.categoryName}</option>
                  </c:forEach>
                </c:if>
              </select>
            </div>
          </div>
          
          <div class="item form-group">
            <label class="control-label col-md-3 col-sm-3 col-xs-12"  for="select">二级分类 <span class="required">*</span></label>
            <div class="col-md-6 col-sm-6 col-xs-12">
              <select name="categoryLevel2" id="categoryLevel2" class="form-control"  required="required">
                <c:if test="${categoryLevel2List != null }">
                  <option value="" selected>--请选择--</option>
                  <c:forEach var="appCategory" items="${categoryLevel2List}">
                    <option <c:if test="${appCategory.id == queryCategoryLevel2}">selected="selected"</c:if>
                            value="${appCategory.id}">${appCategory.categoryName}</option>
                  </c:forEach>
                </c:if>
              </select>
            </div>
          </div>
          
          <div class="item form-group">
            <label class="control-label col-md-3 col-sm-3 col-xs-12" for="select">三级分类 <span class="required">*</span></label>
            <div class="col-md-6 col-sm-6 col-xs-12">
              <select name="categoryLevel3" id="categoryLevel3" class="form-control"  required="required">
                <c:if test="${categoryLevel3List != null }">
                  <option value="" selected>--请选择--</option>
                  <c:forEach var="appCategory" items="${categoryLevel3List}">
                    <option <c:if test="${appCategory.id == queryCategoryLevel3}">selected="selected"</c:if>
                            value="${appCategory.id}">${appCategory.categoryName}</option>
                  </c:forEach>
                </c:if>
              </select>
            </div>
          </div>
          <div class="item form-group">
            <label class="control-label col-md-3 col-sm-3 col-xs-12" for="name">APP状态 <span class="required">*</span>
            </label>
            <div class="col-md-6 col-sm-6 col-xs-12">
            	<input type="hidden" name="status" id="status" value="1">待审核
            </div>
          </div>
          <div class="item form-group">
            <label class="control-label col-md-3 col-sm-3 col-xs-12" for="textarea">应用简介 <span class="required">*</span>
            </label>
            <div class="col-md-6 col-sm-6 col-xs-12">
              <textarea id="appInfo" name="appInfo"     required="required"
              placeholder="请输入本软件的相关信息，本信息作为软件的详细信息进行软件的介绍。" class="form-control col-md-7 col-xs-12"></textarea>
            </div>
          </div>
           <div class="item form-group">
            <label class="control-label col-md-3 col-sm-3 col-xs-12" for="name">LOGO图片 <span class="required">*</span>
            </label>
            <div class="col-md-6 col-sm-6 col-xs-12">
            <input type="file" class="form-control col-md-7 col-xs-12" name="a_logoPicPath" accept="image/jpeg,image/png" required="required" id="a_logoPicPath"/>
            ${fileUploadError }
            </div>
          </div>
          <div class="ln_solid"></div>
          <div class="form-group">
            <div class="col-md-6 col-md-offset-3">
              <button id="send" type="submit" class="btn btn-success">保存</button>
              <button type="button" class="btn btn-primary" id="back" onclick="window.history.back()">返回</button>
              <br/><br/>
            </div>
          </div>
        </form>
      </div>
    </div>
  </div>
</div>
<%@include file="common/footer.jsp"%>
<script type="text/javascript">
  function checkApkName() {
    var apkNameInput = document.getElementById("APKName");
    var apkName = apkNameInput.value;
    $.ajax({
      contentType:JSON,
      data:{"APKName":apkName},
      url:"checkAppName",
      success:function (reply) {
        if (reply == 1){
          window.alert("重复的APK名称！请重新输入！");
          apkNameInput.value = "";
        }
      }
    })
  }
  window.onload = function (){
    var select1 = document.getElementById("categoryLevel1");
    var select2 = document.getElementById("categoryLevel2");
    var select3 = document.getElementById("categoryLevel3");
    select1.onchange = function(){
      if (select1.value != '')
        select1Ajax();
    }
    select2.onchange = function(){
      if (select2.value != '')
        select2Ajax();
    }
    /*var s1Value = select1.value;
    var s2Value = select2.value;*/
    var s1Value = '';
    var s2Value = '';
    select2.onclick = function() {
      var s2Value2 = select2.value;
      //console.log("原"+s2Value+"现"+s2Value2);
      if ((s2Value2 != s2Value && s2Value != '') || s2Value2 == '')
        selectClearChildren(select3);
      s2Value = s2Value2;
    };
    select1.onclick = function() {
      var s1Value1 = select1.value;
      //console.log("原"+s1Value+"现"+s1Value1);
      if ((s1Value1 != s1Value && s1Value != '') || s1Value1 == ''){
        selectClearChildren(select2);
        selectClearChildren(select3);
      }
      s1Value = s1Value1;
    };

    function select1Ajax(){
      var options=$("#queryCategoryLevel1 option:selected");//获取第一个下拉菜单的值
      var ops = document.getElementById("categoryLevel1");
      //jQuery的ajax内容：
      $.ajax({
        contentType:"json",
        /*data: {"parentId":options.val()},*/
        data:{"parentId":ops.value},
        url:"nlc",
        success:function(data){
          //根据data的类型决定是否转换成JSON
          var categoryLevel2List = typeof data == 'string'?JSON.parse(data):data;
          //清空第二个下拉菜单的选项
          selectClearChildren(select2);
          //创建选项
          var element = document.createElement("option");
          element.innerHTML="--请选择--";
          element.setAttribute("value","");
          select2.append(element);
          for (var i = 0; i < categoryLevel2List.length; i++) {
            var newEle = document.createElement("option");
            newEle.innerHTML = categoryLevel2List[i].categoryName;//选项的文本
            newEle.setAttribute("value",categoryLevel2List[i].id)//选项的value，不要写成id了
            select2.appendChild(newEle);
          }
        }});//下方雷同
    }
    function select2Ajax() {
      var options=$("#categoryLevel2 option:selected");
      $.ajax({
        contentType:"json",
        data: {"parentId":options.val()},
        url:"nlc",
        success:function(data){
          var categoryLevel3List = typeof data == 'string'?JSON.parse(data):data;
          selectClearChildren(select3);
          var element = document.createElement("option");
          element.setAttribute("value","0");//此处value为0，避免前端页面无法提交
          element.innerHTML="--请选择--";
          select3.append(element);
          for (var i = 0; i < categoryLevel3List.length; i++) {
            var newEle = document.createElement("option");
            newEle.innerHTML = categoryLevel3List[i].categoryName;
            newEle.setAttribute("value",categoryLevel3List[i].id)
            select3.appendChild(newEle);
          }
        }});
    }
    function selectClearChildren (select) {
      var childrens = select.children;
      if (childrens.length>=1){
        for (var i = childrens.length-1; i >=0; i--) {
          select.removeChild((childrens[i]))
        }}
    }
  }
</script>
<script src="${pageContext.request.contextPath }/statics/localjs/appinfoadd.js"></script>