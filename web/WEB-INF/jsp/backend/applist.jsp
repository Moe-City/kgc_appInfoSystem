<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="common/header.jsp"%>
<head>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">



</head>
<body>

</body>
<div class="clearfix"></div>
<div class="row">

	<div class="col-md-12">
		<div class="x_panel">
			<div class="x_title">
				<h2>
					APP 审核列表${pageNum} <i class="fa fa-user"></i><small>${userSession.userName}
						- 您可以通过搜索或者其他的筛选项对APP的信息进行审核操作。^_^</small>
				</h2>
				<div class="clearfix"></div>
			</div>
			<div class="x_content">
				<form method="post" action="1">
					<input type="hidden" name="pageIndex" value="1" />
			    <ul>
					<li>
						<div class="form-group">
							<label class="control-label col-md-3 col-sm-3 col-xs-12">软件名称</label>
							<div class="col-md-6 col-sm-6 col-xs-12">
								<input name="querySoftwareName" id="querySoftwareName" type="text" class="form-control col-md-7 col-xs-12" value="${querySoftwareName }">
							</div>
						</div>
					</li>
					
					<li>
						<div class="form-group">
							<label class="control-label col-md-3 col-sm-3 col-xs-12">所属平台</label>
							<div class="col-md-6 col-sm-6 col-xs-12">
								<select name="queryFlatformId" id="queryFlatformId" class="form-control">
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
					</li>
					<li>
						<div class="form-group">
							<label class="control-label col-md-3 col-sm-3 col-xs-12">一级分类</label>
							<div class="col-md-6 col-sm-6 col-xs-12">
								<select id="queryCategoryLevel1" name="queryCategoryLevel1" class="form-control">
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
					</li>
					<li>
						<div class="form-group">
							<label class="control-label col-md-3 col-sm-3 col-xs-12">二级分类</label>
							<div class="col-md-6 col-sm-6 col-xs-12">
							<input type="hidden" name="categorylevel2list" id="categorylevel2list"/>
        						<select name="queryCategoryLevel2" id="queryCategoryLevel2" class="form-control">
        							<c:if test="${categoryLevel2List != null }">
									   <option value="">--请选择--</option>
									   <c:forEach var="appCategory" items="${categoryLevel2List}">
									   		<option <c:if test="${appCategory.id == queryCategoryLevel2 }">selected="selected"</c:if>
									   		value="${appCategory.id}">${appCategory.categoryName}</option>
									   </c:forEach>
									</c:if>
        						</select>
							</div>
						</div>
					</li>
					<li>
						<div class="form-group">
							<label class="control-label col-md-3 col-sm-3 col-xs-12">三级分类</label>
							<div class="col-md-6 col-sm-6 col-xs-12">
        						<select name="queryCategoryLevel3" id="queryCategoryLevel3" class="form-control">
        							<c:if test="${categoryLevel3List != null }">
									   <option value="">--请选择--</option>
									   <c:forEach var="appCategory" items="${categoryLevel3List}">
									   		<option <c:if test="${appCategory.id == queryCategoryLevel3 }">selected="selected"</c:if>
									   		value="${appCategory.id}">${appCategory.categoryName}</option>
									   </c:forEach>
									</c:if>
        						</select>
							</div>
						</div>
					</li>
					<li><button type="submit" class="btn btn-primary"> 查 &nbsp;&nbsp;&nbsp;&nbsp;询 </button></li>
				</ul>
			</form>
		</div>
	</div>
</div>
<div class="col-md-12 col-sm-12 col-xs-12">
	<div class="x_panel">
		<div class="x_content">
			<p class="text-muted font-13 m-b-30"></p>
			<div id="datatable-responsive_wrapper"
				class="dataTables_wrapper form-inline dt-bootstrap no-footer">
				<div class="row">
					<div class="col-sm-12">
						<table id="datatable-responsive" class="table table-striped table-bordered dt-responsive nowrap dataTable no-footer dtr-inline collapsed table-hover"
							cellspacing="0" width="100%" role="grid" aria-describedby="datatable-responsive_info" style="width: 100%;">
							<thead>
								<tr role="row">
									<th class="sorting_asc" tabindex="0"
										aria-controls="datatable-responsive" rowspan="1" colspan="1"
										style="width: 70px;" aria-label="First name: activate to sort column descending"
										aria-sort="ascending">软件名称</th>
									<th class="sorting" tabindex="0"
										aria-controls="datatable-responsive" rowspan="1" colspan="1"
										style="width: 10px;"
										aria-label="Last name: activate to sort column ascending">
										APK名称</th>
									<th class="sorting" tabindex="0"
										aria-controls="datatable-responsive" rowspan="1" colspan="1"
										style="width: 90px;"
										aria-label="Last name: activate to sort column ascending">
										软件大小(单位:M)</th>
									<th class="sorting" tabindex="0"
										aria-controls="datatable-responsive" rowspan="1" colspan="1"
										style="width: 50px;"
										aria-label="Last name: activate to sort column ascending">
										所属平台</th>
									<th class="sorting" tabindex="0"
										aria-controls="datatable-responsive" rowspan="1" colspan="1"
										style="width: 170px;"
										aria-label="Last name: activate to sort column ascending">
										所属分类(一级分类、二级分类、三级分类)</th>
									<th class="sorting" tabindex="0"
										aria-controls="datatable-responsive" rowspan="1" colspan="1"
										style="width: 30px;"
										aria-label="Last name: activate to sort column ascending">
										状态</th>
									<th class="sorting" tabindex="0"
										aria-controls="datatable-responsive" rowspan="1" colspan="1"
										style="width: 30px;"
										aria-label="Last name: activate to sort column ascending">
										下载次数</th>
									<th class="sorting" tabindex="0"
										aria-controls="datatable-responsive" rowspan="1" colspan="1"
										style="width: 64px;"
										aria-label="Last name: activate to sort column ascending">
										最新版本号</th>
									<th class="sorting" tabindex="0"
										aria-controls="datatable-responsive" rowspan="1" colspan="1"
										style="width: 30px;"
										aria-label="Last name: activate to sort column ascending">
										操作</th>
								</tr>
							</thead>
							<tbody>
								<c:forEach var="appInfo" items="${pages.list }" varStatus="status">
									<tr role="row" class="odd">
										<td tabindex="0" class="sorting_1">${appInfo.softwareName}</td>
										<td>${appInfo.APKName }</td>
										<td>${appInfo.softwareSize }</td>
										<td>${appInfo.flatformName }</td>
										<td>${appInfo.categoryLevel1Name } -> ${appInfo.categoryLevel2Name } -> ${appInfo.categoryLevel3Name }</td>
										<td>${appInfo.statusName }</td>
										<td>${appInfo.downloads }</td>
										<td>${appInfo.versionNo }</td>
										<td>
										<button type="button" class="btn btn-default checkApp"
											appinfoid="${appInfo.id }" versionid="${appInfo.versionId }" status="${appInfo.status }" statusname="${appInfo.statusName }"
											data-toggle="tooltip" data-placement="top" title="" data-original-title="查看并审核APP"
												onclick='checkThis(document.forms[2],"${appInfo.id }", "${appInfo.versionId }", "${appInfo.status }", "${appInfo.statusName }")'>审核</button>
										</td>
									</tr>
								</c:forEach>
							</tbody>
						</table>
					</div>
				</div>
				<div class="row">
					<div class="col-sm-5">
						<div class="dataTables_info" id="datatable-responsive_info"
							role="status" aria-live="polite">共${pages.total }条记录
							${pages.pageNum }/${pages.pages }页</div>
					</div>
					<div class="col-sm-7">
						<div class="dataTables_paginate paging_simple_numbers"
							id="datatable-responsive_paginate">
							<ul class="pagination">
								<c:if test="${pages.pageNum > 1}">
									<li class="paginate_button previous"><a
										href="javascript:page_nav(document.forms[1],1);"
										aria-controls="datatable-responsive" data-dt-idx="0"
										tabindex="0">首页</a>
									</li>
									<li class="paginate_button "><a
										href="javascript:page_nav(document.forms[1],${pages.pageNum-1});"
										aria-controls="datatable-responsive" data-dt-idx="1"
										tabindex="0">上一页</a>
									</li>
								</c:if>
								<c:if test="${pages.pageNum < pages.pages }">
									<li class="paginate_button "><a
										href="javascript:page_nav(document.forms[1],${pages.pageNum+1 });"
										aria-controls="datatable-responsive" data-dt-idx="1"
										tabindex="0">下一页</a>
									</li>
									<li class="paginate_button next"><a
										href="javascript:page_nav(document.forms[1],${pages.pages });"
										aria-controls="datatable-responsive" data-dt-idx="7"
										tabindex="0">最后一页</a>
									</li>
								</c:if>
							</ul>
						</div>
					</div>
				</div>
			</div>

		</div>
	</div>
</div>
</div>
<form method="post">
	<label>
		<input type="hidden" name="querySoftwareName" value="${querySoftwareName}"/>
		<input type="hidden" name="queryFlatformId" value="${queryFlatformId}"/>
		<input type="hidden" name="queryCategoryLevel1" value="${queryCategoryLevel1}"/>
		<input type="hidden" name="queryCategoryLevel2" value="${queryCategoryLevel2}"/>
		<input type="hidden" name="queryCategoryLevel3" value="${queryCategoryLevel3}"/>
	</label>
</form>
<form method="post" action="check">
	<label>
		<input type="hidden" id="formAppinfoid" name="formAppinfoid"/>
		<input type="hidden" id="formVersionid" name="formVersionid"/>
		<input type="hidden" id="formStatus" name="formStatus"/>
		<input type="hidden" id="formStatusname" name="formStatusname"/>
	</label>
</form>
<%@include file="common/footer.jsp"%>
<script type="text/javascript">

	function checkThis(form, appinfoid, versionid, status, statusname) {
		if (versionid == 0){
			window.alert("该APP没有上传最新版本，不能进行审核操作！");
			return;
		}
		var aiid = document.getElementById("formAppinfoid");
		aiid.value = appinfoid;
		var vid = document.getElementById("formVersionid");
		vid.value = versionid;
		var ss = document.getElementById("formStatus");
		ss.value = status;
		var ssn = document.getElementById("formStatusname");
		ssn.value = statusname;
		form.action="check";
		form.submit();
	}

	function page_nav(form, pageNum) {
		form.action=pageNum;
		form.submit();
	}

	window.onload = function (){
		//获取下拉菜单
		var select1 = document.getElementById("queryCategoryLevel1");
		var select2 = document.getElementById("queryCategoryLevel2");
		var select3 = document.getElementById("queryCategoryLevel3");
		select1.onchange = function(){if (select1.value != '')select1Ajax();}
		select2.onchange = function(){if (select2.value != '')select2Ajax();}
		var s1Value = select1.value;
		var s2Value = select2.value;
		select2.onclick = function() {
			var s2Value2 = select2.value;
			//console.log("原"+s2Value+"现"+s2Value2);
			if ((s2Value2 != s2Value && s2Value != '') || s2Value2 == '')
				selectClearChildren(select3);
		};
		select1.onclick = function() {
			var s1Value1 = select1.value;
			//console.log("原"+s1Value+"现"+s1Value1);
			if ((s1Value1 != s1Value && s1Value != '') || s1Value1 == ''){
				selectClearChildren(select2);
				selectClearChildren(select3);
			}
		};

		function select1Ajax(){
			var options=$("#queryCategoryLevel1 option:selected");//获取第一个下拉菜单的值
			var ops = document.getElementById("queryCategoryLevel1");
			//jQuery的ajax内容：
			$.ajax({
				contentType:"json",
				/*data: {"parentId":options.val()},*/
				data:{"parentId":ops.value},
				url:"nextLevelCategory",
				success:function(data){
					//根据data的类型决定是否转换成JSON，似乎不用转换（好像自动转好了）
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
			var options=$("#queryCategoryLevel2 option:selected");
			$.ajax({
				contentType:"json",
				data: {"parentId":options.val()},
				url:"nextLevelCategory",
				success:function(data){
					var categoryLevel3List = typeof data == 'string'?JSON.parse(data):data;
					selectClearChildren(select3);
					var element = document.createElement("option");
					element.setAttribute("value","");
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
<script src="${pageContext.request.contextPath }/statics/localjs/rollpage.js"></script>
<script src="${pageContext.request.contextPath }/statics/localjs/applist.js"></script>