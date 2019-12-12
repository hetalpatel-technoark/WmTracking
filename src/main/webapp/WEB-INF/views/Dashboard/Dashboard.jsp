<%-- 
    Document   : Dashboard
    Created on : Nov 25, 2019, 6:26:06 PM
    Author     : Admin
--%>
<%@page import="com.wmtrucking.entities.MaJobs"%>
<%@page import="java.util.List"%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<jsp:include page="../Template/header.jsp"></jsp:include>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/assets-new/app-assets/vendors/css/charts/apexcharts.css">
<link href="https://fonts.googleapis.com/css?family=Montserrat:300,400,500,600" rel="stylesheet">
<link href="https://fonts.googleapis.com/css?family=Montserrat:300,400,500,600" rel="stylesheet">
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
                            <li class="breadcrumb-item"><a href="<%= request.getContextPath()%>/Dashboard/Dashboard">Home</a>
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
                            <h2 class="text-bold-700 mt-1"><%= request.getAttribute("customer")%></h2>
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
                            <h2 class="text-bold-700 mt-1"><%= request.getAttribute("driver")%></h2>
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
                            <h2 class="text-bold-700 mt-1"><%= request.getAttribute("job")%></h2>
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

        <section id="">
            <div class="row">

                <div class="col-lg-12 col-md-12">
                    <div class="card">
                        <div class="row">

                            <div class="col-lg-6">
                                <div class="card-header">
                                    <h4 class="card-title">Pickup Site </h4>
                                </div>  
                                <div class="card-content">

                                    <div id="gmaps-basic-maps"> 
                                        <div id="pickupMap" class="height-400"></div>
                                    </div>
                                </div></div> 
                            <div class="col-lg-6">
                                <div class="card-header">
                                    <h4 class="card-title"> Dump Site </h4>
                                </div>  
                                <div class="card-content">
                                    <div id="gmaps-basic-maps"> 
                                        <div id="dumpMap" class="height-400"></div>
                                    </div> 
                                </div></div></div>
                    </div>
                </div>
            </div>
        </section>
    </div>
</div>

<jsp:include page="../Template/pageEnd.jsp"></jsp:include>    

    <script src="//maps.googleapis.com/maps/api/js?key=AIzaSyBgjNW0WA93qphgZW-joXVR6VC3IiYFjfo"></script>
    <script src="<%=request.getContextPath()%>/assets-new/app-assets/vendors/js/charts/gmaps.min.js"></script>

<jsp:include page="../Template/footer.jsp"></jsp:include>
<script src="<%=request.getContextPath()%>/assets-new/app-assets/js/scripts/charts/gmaps/maps.js"></script>
<script src="<%=request.getContextPath()%>/assets-new/app-assets/vendors/js/charts/apexcharts.min.js"></script>
<script src="<%=request.getContextPath()%>/assets-new/app-assets/vendors/js/extensions/tether.min.js"></script>
<script src="<%=request.getContextPath()%>/assets-new/app-assets/vendors/js/extensions/shepherd.min.js"></script>
<script src="<%=request.getContextPath()%>/assets-new/app-assets/js/scripts/cards/card-statistics.js"></script>
<script>
    <% String cnt = "", month = "";
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
    var lineChartOptionsA = {
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
            tickAmount: 1,
        }
    }
    var lineChartA = new ApexCharts(
            document.querySelector("#line-chart"),
            lineChartOptionsA
            );
    lineChartA.render();

    $(document).ready(function () {

    <% List<MaJobs> maJobses = (List<MaJobs>) request.getAttribute("maJobsesList");%>
        //Pickup Site Map
        map = new GMaps({
            div: '#pickupMap',
            lat: <%= maJobses.size() > 0 ? maJobses.get(0).getFromlatitude() : "23.0267556"%>,
            lng: <%= maJobses.size() > 0 ? maJobses.get(0).getFromlongitude() : "72.6008286"%>,
            zoom: 15
        });
    <% for (MaJobs maJobs : maJobses) {%>
        map.addMarker({
            lat: <%= maJobs.getFromlatitude()%>,
            lng: <%= maJobs.getFromlongitude()%>,
            title: '<%= maJobs.getJobname()%>',
            infoWindow: {
                content: '<p><%= maJobs.getJobname()%></p>'
            }
        });
    <%}%>

        map = new GMaps({
        div: "#pickupMap",
                lat: <%= maJobses.size() > 0 ? maJobses.get(0).getFromlatitude() : "23.0267556"%>,
                lng: <%= maJobses.size() > 0 ? maJobses.get(0).getFromlongitude() : "72.6008286"%>,
                zoom: 15
        })<%= maJobses.size() > 0 ? "," : ""%>
    <%
        for (MaJobs maJobs2 : maJobses) {%>
        map.addMarker({
            lat: <%= maJobs2.getFromlatitude()%>,
            lng: <%= maJobs2.getFromlongitude()%>,
            title: '<%= maJobs2.getJobname()%>',
            infoWindow: {
                content: "<p>Job name: <%= maJobs2.getJobname()%></p>"
            }
        });
    <%}%>

//Dumping map
        map = new GMaps({
            div: '#dumpMap',
            lat: <%= maJobses.size() > 0 ? maJobses.get(0).getTolatitude() : "23.0267556"%>,
            lng: <%= maJobses.size() > 0 ? maJobses.get(0).getTolongitude() : "72.6008286"%>,
            zoom: 15
        });
    <% for (MaJobs maJobs : maJobses) {%>
        map.addMarker({
            lat: <%= maJobs.getTolatitude()%>,
            lng: <%= maJobs.getTolongitude()%>,
            title: '<%= maJobs.getJobname()%>',
            infoWindow: {
                content: '<p><%= maJobs.getJobname()%></p>'
            }
        });
    <%}%>

        map = new GMaps({
          div: '#dumpMap',
            lat: <%= maJobses.size() > 0 ? maJobses.get(0).getTolatitude() : "23.0267556"%>,
            lng: <%= maJobses.size() > 0 ? maJobses.get(0).getTolongitude() : "72.6008286"%>,
            zoom: 15
        })<%= maJobses.size() > 0 ? "," : ""%>
    <%
        for (MaJobs maJobs2 : maJobses) {%>
        map.addMarker({
            lat: <%= maJobs2.getTolatitude()%>,
            lng: <%= maJobs2.getTolongitude()%>,
            title: '<%= maJobs2.getJobname()%>',
            infoWindow: {
                content: "<p>Job name: <%= maJobs2.getJobname()%></p>"
            }
        });
    <%}%>


    });

</script>