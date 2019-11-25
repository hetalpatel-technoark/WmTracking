<%-- 
    Document   : Dashboard
    Created on : Nov 25, 2019, 6:26:06 PM
    Author     : Admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<jsp:include page="../Template/header.jsp"></jsp:include>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/assets-new/app-assets/vendors/css/charts/apexcharts.css">

<div class="content-wrapper">
    <div class="content-header row">
        <div class="content-header-left col-md-9 col-12 mb-2">
            <div class="row breadcrumbs-top">
                <div class="col-12">
                    <h2 class="content-header-title float-left mb-0">Dashboard</h2>
                    <div class="breadcrumb-wrapper col-12">
                        <ol class="breadcrumb">
                            <li class="breadcrumb-item"><a href="<%= request.getContextPath()%>/AdminDashboard/">Home</a>
                            </li>

                        </ol>
                    </div>
                </div>
            </div>
        </div>

    </div></div>

<jsp:include page="../Template/pageEnd.jsp"></jsp:include>    

    <!-- BEGIN Vendor JS-->
<jsp:include page="../Template/footer.jsp"></jsp:include>
<script src="<%=request.getContextPath()%>/assets-new/app-assets/vendors/js/charts/apexcharts.min.js"></script>
<script src="<%=request.getContextPath()%>/assets-new/app-assets/vendors/js/extensions/tether.min.js"></script>
<script src="<%=request.getContextPath()%>/assets-new/app-assets/vendors/js/extensions/shepherd.min.js"></script>

<script src="<%=request.getContextPath()%>/assets-new/app-assets/js/scripts/cards/card-statistics.js"></script>

