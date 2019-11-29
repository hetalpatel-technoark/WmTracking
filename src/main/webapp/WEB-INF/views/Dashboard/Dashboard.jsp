<%-- 
    Document   : Dashboard
    Created on : Nov 25, 2019, 6:26:06 PM
    Author     : Admin
--%>
<%@page import="java.util.List"%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<jsp:include page="../Template/header.jsp"></jsp:include>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/assets-new/app-assets/vendors/css/charts/apexcharts.css">
<style>
    .avatar1 {
        white-space: nowrap;
        background-color: #C3C3C3;
        border-radius: 50%;
        position: relative;

        color: #FFFFFF;
        display: -webkit-inline-box;
        display: -webkit-inline-flex;
        display: -moz-inline-box;
        display: -ms-inline-flexbox;
        display: inline-flex;
        font-size: 0.75rem;
        text-align: center;
        vertical-align: middle;
        margin: 5px;
    }
</style>
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
    </div>

    <div class="content-body">
        <!-- Analytics card section start -->
        <section id="apexchart">
            <div class="row">

                <div class="col-lg-4 col-md-12">
                    <div class="card">
                        <div class="card-header d-flex flex-column align-items-start pb-0">
                            <div class="avatar bg-rgba-primary p-50 m-0">
                                <div class="avatar-content">
                                    <i class="feather icon-users   text-primary font-medium-5"></i>
                                </div>
                            </div>
                            <h2 class="text-bold-700 mt-1"><%= request.getAttribute("customer") %></h2>
                            <p class="mb-0">Total Customers</p>
                        </div><br>
                    </div>
                </div> 
                <div class="col-lg-4 col-md-12">
                    <div class="card">
                        <div class="card-header d-flex flex-column align-items-start pb-0">
                            <div class="avatar bg-rgba-primary p-50 m-0">
                                <div class="avatar-content">
                                    <i class="feather icon-user  text-primary font-medium-5"></i>
                                </div>
                            </div>
                            <h2 class="text-bold-700 mt-1"><%= request.getAttribute("driver") %></h2>
                            <p class="mb-0">Total Drivers</p>
                        </div>
                        <br>
                    </div>
                </div>
                <div class="col-lg-4 col-md-12">
                    <div class="card">
                        <div class="card-header d-flex flex-column align-items-start pb-0">
                            <div class="avatar bg-rgba-primary p-50 m-0">
                                <div class="avatar-content">
                                    <i class="feather icon-award  text-primary font-medium-5"></i>
                                </div>
                            </div>
                            <h2 class="text-bold-700 mt-1"><%= request.getAttribute("job") %></h2>
                            <p class="mb-0">Total Jobs</p>
                        </div><br>
                    </div>
                </div>

            </div>
        </section>
        <section id="apexchart">
            <div class="row">
                <!-- Line Chart -->
                <div class="col-lg-12 col-md-12">
                    <div class="card">

                        <div class="card-header">
                            <h4 class="card-title">Job for Month Base </h4>
                        </div>  
                        <div class="card-content">
                            <div class="card-body">
                                <div id="line-chart"></div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </section>
        <section id="apexchart">
            <div class="row">
                <!-- Line Chart -->
                <div class="col-lg-12 col-md-12">
                    <div class="card">

                        <div class="card-header">
                            <h4 class="card-title">Job for Driver Base </h4>
                        </div>  
                        <div class="card-content">
                            <div class="card-body">
                                <div id="line-chart_driver"></div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </section>
        <section id="apexchart">
            <div class="row">
                <!-- Line Chart -->
                <div class="col-lg-12 col-md-12">
                    <div class="card">

                        <div class="card-header">
                            <h4 class="card-title">Job for Customer Base </h4>
                        </div>  
                        <div class="card-content">
                            <div class="card-body">
                                <div id="line-chart_customer"></div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </section>
    </div>
</div>

<jsp:include page="../Template/pageEnd.jsp"></jsp:include>    

    <!-- BEGIN Vendor JS-->
<jsp:include page="../Template/footer.jsp"></jsp:include>
<script src="<%=request.getContextPath()%>/assets-new/app-assets/vendors/js/charts/apexcharts.min.js"></script>
<script src="<%=request.getContextPath()%>/assets-new/app-assets/vendors/js/extensions/tether.min.js"></script>
<script src="<%=request.getContextPath()%>/assets-new/app-assets/vendors/js/extensions/shepherd.min.js"></script>

<script src="<%=request.getContextPath()%>/assets-new/app-assets/js/scripts/cards/card-statistics.js"></script>

<script>

    <%
        String cnt = "", month = "";
        if (request.getAttribute("monthWiseJob") != null) {
            List<Object[]> monthWiseJob = (List<Object[]>) request.getAttribute("monthWiseJob");
           // int[] a = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12};

            for (int i = 0; i < monthWiseJob.size(); i++) {
                if (i == monthWiseJob.size()) {
                    cnt += monthWiseJob.get(i)[1];
                    month += "\'" + monthWiseJob.get(i)[0] + "\'";
                } else {
                    cnt += monthWiseJob.get(i)[1] + ",";
                    month += "\'" + monthWiseJob.get(i)[0] + "\'" + ",";
                }
            }
        }
    %>
    var lineChartOptions = {
        chart: {
            height: 350,
            type: 'line',
            zoom: {
                enabled: false
            }
        },
        colors: ['#7367F0'],
        dataLabels: {
            enabled: false
        },
        stroke: {
            curve: 'straight'
        },
        series: [{
                name: "Job",
                data: [<%=cnt%>],
            }],
        title: {
            // text: 'Product Trends by Month',
            align: 'left'
        },
        grid: {
            row: {
                colors: ['#f3f3f3', 'transparent'], // takes an array which will be repeated on columns
                opacity: 0.5
            },
        },
        xaxis: {
            categories: [<%=month%>],
        },
        yaxis: {
            tickAmount: 5,
        }
    }
    var lineChart = new ApexCharts(
            document.querySelector("#line-chart"),
            lineChartOptions
            );
    lineChart.render();

//Driver wise job
    <%  String cntD = "", monthD = "";
    if (request.getAttribute("DriverWiseJob") != null) {
    List < Object[] > DriverWiseJob = (List < Object[] > ) request.getAttribute("DriverWiseJob");
    for (int i = 0; i < DriverWiseJob.size(); i++) {
    if (i == DriverWiseJob.size()) {
    cntD += DriverWiseJob.get(i)[1];
    monthD += "\'" + DriverWiseJob.get(i)[0] + "\'";
    } else {
    cntD += DriverWiseJob.get(i)[1] + ",";
    monthD += "\'" + DriverWiseJob.get(i)[0] + "\'" + ",";
    }
    }
    }
    %>
    var lineChartOptions = {
        chart: {
            height: 350,
            type: 'line',
            zoom: {
                enabled: false
            }
        },
        colors: ['#7367F0'],
        dataLabels: {
            enabled: false
        },
        stroke: {
            curve: 'straight'
        },
        series: [{
                name: "Job",
                data: [<%=cntD%>],
            }],
        title: {
            // text: 'Product Trends by Month',
            align: 'left'
        },
        grid: {
            row: {
                colors: ['#f3f3f3', 'transparent'], // takes an array which will be repeated on columns
                opacity: 0.5
            },
        },
        xaxis: {
            categories: [<%=monthD%>],
        },
        yaxis: {
            tickAmount: 5,
        }
    }
    var lineChart = new ApexCharts(
            document.querySelector("#line-chart_driver"),
            lineChartOptions
            );
    lineChart.render();

    //Customer wise job
    <%  String cntC = "", monthC = "";
    if (request.getAttribute("customerWiseJob") != null) {
    List < Object[] > customerWiseJob = (List < Object[] > ) request.getAttribute("customerWiseJob");
    for (int i = 0; i < customerWiseJob.size(); i++) {
    if (i == customerWiseJob.size()) {
    cntC += customerWiseJob.get(i)[1];
    monthC += "\'" + customerWiseJob.get(i)[0] + "\'";
    } else {
    cntC += customerWiseJob.get(i)[1] + ",";
    monthC += "\'" + customerWiseJob.get(i)[0] + "\'" + ",";
    }
    }
    }
    %>
    var lineChartOptions = {
        chart: {
            height: 350,
            type: 'line',
            zoom: {
                enabled: false
            }
        },
        colors: ['#7367F0'],
        dataLabels: {
            enabled: false
        },
        stroke: {
            curve: 'straight'
        },
        series: [{
                name: "Job",
                data: [<%=cntC%>],
            }],
        title: {
            // text: 'Product Trends by Month',
            align: 'left'
        },
        grid: {
            row: {
                colors: ['#f3f3f3', 'transparent'], // takes an array which will be repeated on columns
                opacity: 0.5
            },
        },
        xaxis: {
            categories: [<%=monthC%>],
        },
        yaxis: {
            tickAmount: 5,
        }
    }
    var lineChart = new ApexCharts(
            document.querySelector("#line-chart_customer"),
            lineChartOptions
            );
    lineChart.render();

</script>