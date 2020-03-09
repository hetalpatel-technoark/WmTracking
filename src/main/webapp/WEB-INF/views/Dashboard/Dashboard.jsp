<%-- 
    Document   : Dashboard
    Created on : Nov 25, 2019, 6:26:06 PM
    Author     : Admin
--%>
<%@page import="com.wmtrucking.utils.CheckInput"%>
<%@page import="com.wmtrucking.utils.DateUtils"%>
<%@page import="java.util.Date"%>
<%@page import="com.wmtrucking.pojo.JobPojo"%>
<%@page import="com.wmtrucking.entities.MaJobs"%>
<%@page import="java.util.List"%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<jsp:include page="../Template/header.jsp"></jsp:include>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/assets-new/app-assets/vendors/css/charts/apexcharts.css">
<link href="https://fonts.googleapis.com/css?family=Montserrat:300,400,500,600" rel="stylesheet">
<link href="https://fonts.googleapis.com/css?family=Montserrat:300,400,500,600" rel="stylesheet">

<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/assets-new/app-assets/vendors/css/vendors.min.css">
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/assets-new/app-assets/vendors/css/tables/datatable/datatables.min.css">
<style>
    .label {
        border-radius: 6px !important ;
        margin-bottom: 3px;
    }
    .label-orange {
        background-color: #637a91;
    }
    .label-pur {
        background-color: #f092b0;
    }
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
        <div class="content-header-left col-md-12 col-12 mb-2">
            <div class="row breadcrumbs-top">
                <div class="col-6">
                    <h3 class="content-header-title float-left mb-0">Interactive Dashboard</h3>
                    <div class="breadcrumb-wrapper col-12">
                        <ol class="breadcrumb">
                            <li class="breadcrumb-item"><a href="<%= request.getContextPath()%>/Dashboard/Dashboard">Home</a>
                            </li>

                        </ol>

                    </div>
                </div>
                <div class="col-6">
                    <div class="breadcrumb-wrapper col-12">
                        <h5 class="content-header-title float-left mb-0" style="vertical-align: middle;margin-top: 0.4em; margin-right: 0;">
                            Report Date :</h5>
                        <ul class="pagination justify-content-center mt-2" style="margin-top: 0px !important;">

                            <li class="page-item">
                                <a class="page-link" href="<%= request.getContextPath()%>/Dashboard/PrevJobDate/<%= request.getAttribute("jobDate")%>">
                                    <i class="feather icon-chevrons-left"></i> Prev 
                                </a></li>
                            <li class="page-item active"><a class="page-link" href="#"><%= request.getAttribute("showJobDate") != null ? request.getAttribute("showJobDate") : request.getAttribute("jobDate")%></a></li>

                            <li class="page-item">
                                <a class="page-link" href="<%= request.getContextPath()%>/Dashboard/NextJobDate/<%= request.getAttribute("jobDate")%>"> Next <i class="feather icon-chevrons-right"></i> 
                                </a></li>
                        </ul>
                    </div>
                </div>
            </div>
        </div>
    </div>

    <div class="content-body">
        <!-- Analytics card section start -->
        <section id="">
            <div class="row">

                <div class="col-lg-12 col-md-12">
                    <div class="card">
                        <div class="row">

                            <div class="col-lg-12">
                                <div class="card-header">
                                    <div class="col-lg-6">
                                        <h4 class="card-title">Job List  </h4></div>

                                    <div class="col-lg-5  input-group">                                        

                                        <!--                                            <input id="myInput" class="form-control" onkeyup="myFunction()" type="text" placeholder="Search by Job Name/Number..">-->
                                        <input id="myInput" class="form-control"  type="text" placeholder="Search by Job Name/Number..">
                                        <a class="btn bg-gradient-primary" href="javascript:void(0);" onclick="getOwnerList('<%=request.getContextPath()%>/Dashboard/getJobList', '<%= request.getAttribute("jobDate")%>')" style="border-radius:0rem !important">
                                            <i class="fa fa-search"></i> Search</a>
                                    </div>

                                </div>  
                                <div class="card-content">

                                    <div class="card-body card-dashboard">
                                        <div class="table-responsive">
                                            <table id="myTable" class="table zero-configuration">
                                                <thead>
                                                    <tr>     
                                                        <th>Job Name</th>                                             
                                                        <th>Job Number</th>                                             
                                                        <th>Job Date</th>                                                          
                                                        <th> Total Dumps</th>
                                                        <th>Pickup Dumps</th>
                                                        <th>Completed Dumps</th>
                                                        <th>Job Status</th>  
                                                        <th> Total Drivers </th>
                                                    </tr>
                                                </thead>
                                                <tbody id="row_dashboard__list">                                          
                                                    <%
                                                        CheckInput checkInput = new CheckInput();
                                                        List<JobPojo> majobs = (List<JobPojo>) request.getAttribute("maJobsesList");
                                                        if (!majobs.isEmpty()) {
                                                            for (JobPojo majob : majobs) {
                                                    %>
                                                    <tr >
                                                        <td><%=checkInput.checkValue(majob.getJobname())%></td>
                                                        <td><%=checkInput.checkValue(majob.getJobnumber())%></td>
                                                        <td><%=checkInput.checkValue(majob.getJobdate())%></td>  
                                                        <td><%=checkInput.checkValue(majob.getTotaldumps())%></td>
                                                        <td><%=checkInput.checkValue(majob.getPickupddumps())%></td>
                                                        <td><%=checkInput.checkValue(majob.getCompleteddumps())%></td>
                                                        <% if (majob.getJobStatus().equals("Completed")) {%>
                                                        <td><span class="label label-success" >Completed</span></td>
                                                        <%} else if (majob.getJobStatus().equals("Active")) {%>
                                                        <td><span class="label label-info " >Active</span></td>
                                                        <%} else if (majob.getJobStatus().equals("Pending")) {%>
                                                        <td><span class="label label-danger " >Pending</span></td>
                                                        <%}%>
                                                        <td><%=checkInput.checkValue(majob.getDrivercount())%></td>  
                                                    </tr>

                                                    <%   }
                                                    } else {%>                                                    
                                                    <tr><td colspan="7"><center><b>No records found</b></center></td>
                                                </tr>
                                                <%}%>
                                                </tbody>
                                                <tbody style="display: none;" id="row_search_list">

                                                </tbody>
                                            </table>
                                        </div>

                                    </div>
                                </div></div> 

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

                            <div class="col-lg-12">
                                <div class="card-header">
                                    <h4 class="card-title">Daily Report  </h4>
                                </div>  
                                <div class="card-content">

                                    <div id="gmaps-basic-maps"> 
                                        <!--                                        <div id="pickupMap" class="height-400"></div>-->

                                        <div id="pickupMap" class="height-400"></div>
                                    </div>
                                </div></div> 

                        </div>
                    </div>
                </div>
            </div>
        </section>
        <section id="apexchart">
            <div class="row">
                <div class="col-lg-2 col-md-12" style="padding: 0px"  >
                    <div class="card"  style="height: 80%;">
                        <div class="card-header d-flex flex-column align-items-start pb-0">
                            <div class="avatar bg-rgba-primary p-50 m-0">
                                <div class="avatar-content">
                                    <i class="feather icon-at-sign   text-primary font-medium-5"></i>
                                </div>
                            </div>
                            <h2 class="text-bold-700 mt-1">
                                <%= request.getAttribute("job") != null ? request.getAttribute("job") : "0"%>
                            </h2>
                            <p class="mb-0" style="color: black">Total Job</p>
                        </div><br>

                    </div>
                </div> 
                <div class="col-lg-2 col-md-12"  >
                    <div class="card"  style="height: 80%;">
                        <div class="card-header d-flex flex-column align-items-start pb-0">
                            <div class="avatar bg-rgba-primary p-50 m-0">
                                <div class="avatar-content">
                                    <i class="feather icon-users   text-primary font-medium-5"></i>
                                </div>
                            </div>
                            <h2 class="text-bold-700 mt-1">
                                <%= request.getAttribute("totalDump") != null ? request.getAttribute("totalDump") : "0"%>
                            </h2>
                            <p class="mb-0" style="color: black">Total Dumps</p>
                        </div><br>

                    </div>
                </div> 
                <div class="col-lg-2 col-md-12" style="padding: 0px"  >
                    <div class="card"  style="height: 80%;">
                        <a href="<%= request.getContextPath()%>/Dashboard/DumpsList/<%= request.getAttribute("jobDate")%>?flag=start" >
                            <div class="card-header d-flex flex-column align-items-start pb-0">
                                <div class="avatar bg-rgba-primary p-50 m-0">
                                    <div class="avatar-content">
                                        <i class="feather icon-users   text-primary font-medium-5"></i>
                                    </div>
                                </div>
                                <h2 class="text-bold-700 mt-1">
                                    <%= request.getAttribute("countDumpingPickup") != null ? request.getAttribute("countDumpingPickup") : "0"%>
                                </h2>
                                <p class="mb-0" style="color: black" >Total Dumps Pickup </p>
                            </div><br>
                        </a>
                    </div>
                </div> 
                <div class="col-lg-2 col-md-12" >
                    <div class="card"  style="height: 80%;"  >
                        <a href="<%= request.getContextPath()%>/Dashboard/DumpsList/<%= request.getAttribute("jobDate")%>?flag=end" >

                            <div class="card-header d-flex flex-column align-items-start pb-0">
                                <div class="avatar bg-rgba-primary p-50 m-0">
                                    <div class="avatar-content">
                                        <i class="feather icon-user  text-primary font-medium-5"></i>
                                    </div>
                                </div>
                                <h2 class="text-bold-700 mt-1">
                                    <%= request.getAttribute("countDumpingDone") != null ? request.getAttribute("countDumpingDone") : "0"%>
                                </h2>

                                <!--                            <p class="mb-0">Total Drivers</p>-->
                                <p class="mb-0" style="color: black">Total Dumps Done </p>
                            </div></a>
                        <br>
                    </div>
                </div>
                <div class="col-lg-2 col-md-12" style="padding: 0px" >
                    <div class="card"  style="height: 80%;">
                        <div class="card-header d-flex flex-column align-items-start pb-0">
                            <div class="avatar bg-rgba-primary p-50 m-0">
                                <div class="avatar-content">
                                    <i class="feather icon-award  text-primary font-medium-5"></i>
                                </div>
                            </div>
                            <h2 class="text-bold-700 mt-1">
                                <%= request.getAttribute("driver")%>
                            </h2>
                            <!--                            <p class="mb-0">Total Jobs</p>-->
                            <p class="mb-0">Total Driver Assigned </p>
                        </div><br>
                    </div>
                </div>
                <div class="col-lg-2 col-md-12"  >
                    <div class="card"  style="height: 80%;" >
                        <div class="card-header d-flex flex-column align-items-start pb-0">
                            <div class="avatar bg-rgba-primary p-50 m-0">
                                <div class="avatar-content">
                                    <i class="feather icon-award  text-primary font-medium-5"></i>
                                </div>
                            </div>
                            <h2 class="text-bold-700 mt-1">
                                <%= request.getAttribute("customer")%>
                            </h2>
                            <p class="mb-0">Total Companies </p>
                        </div><br>
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
<!--<script src="<%=request.getContextPath()%>/assets-new/app-assets/js/scripts/charts/gmaps/maps.js"></script>-->
<script src="<%=request.getContextPath()%>/assets-new/app-assets/vendors/js/charts/apexcharts.min.js"></script>
<script src="<%=request.getContextPath()%>/assets-new/app-assets/vendors/js/extensions/tether.min.js"></script>
<script src="<%=request.getContextPath()%>/assets-new/app-assets/vendors/js/extensions/shepherd.min.js"></script>
<script src="<%=request.getContextPath()%>/assets-new/app-assets/js/scripts/cards/card-statistics.js"></script>
<!--<script src="<%=request.getContextPath()%>/assets-new/app-assets/js/scripts/charts/gmaps/maps.min.js"></script>-->

<script>
                                            function getOwnerList(url, jobdate) {
                                                var request = $.ajax({
                                                    url: url,
                                                    method: "POST",
                                                    data: {searchtext: $('#myInput').val(), jobdate: jobdate},
                                                    dataType: "json",
                                                    success: function (data) {
                                                        $('#row_search_list').html(data.table);
                                                        $('#row_search_list').show();
                                                        $('#row_dashboard__list').hide();
                                                        mApp.unblock("#row_search_list");
                                                    }, beforeSend: function (xhr) {
                                                        if ($('#myInput').val() == '' || $('#myInput').val() == undefined || $('#myInput').val() == null) {
                                                            $.growl.error({message: "Please enter some value in textbox"});
                                                            mApp.unblock("#row_search_list");
                                                            return false;
                                                        }
                                                    }
                                                });
                                            }

//                                            function myFunction() {
//                                                var input, filter, table, tr, td_col1, td_col2, i, txtValue_col1, txtValue_col2;
//                                                input = document.getElementById("myInput");
//                                                filter = input.value.toUpperCase();
//                                                table = document.getElementById("myTable");
//                                                tr = table.getElementsByTagName("tr");
//                                                for (i = 0; i < tr.length; i++) {
//                                                    td_col1 = tr[i].getElementsByTagName("td")[0];
//                                                    td_col2 = tr[i].getElementsByTagName("td")[1];
//                                                    if (td_col1 || td_col2) {
//                                                        txtValue_col1 = td_col1.textContent || td_col1.innerText;
//                                                        txtValue_col2 = td_col2.textContent || td_col2.innerText;
//                                                        if (txtValue_col1.toUpperCase().indexOf(filter) > -1) {
//                                                            tr[i].style.display = "";
//                                                        } else if (txtValue_col2.toUpperCase().indexOf(filter) > -1) {
//                                                            tr[i].style.display = "";
//                                                        } else {
//                                                            tr[i].style.display = "none";
//                                                        }
//                                                    }
//                                                }
//                                            }
                                            $(document).ready(function () {
    <% List<JobPojo> maJobses = (List<JobPojo>) request.getAttribute("maJobsesList");%>
                                                //Pickup Site Map
                                                map = new GMaps({
                                                    div: '#pickupMap',
                                                    lat: <%= maJobses.size() > 0 ? maJobses.get(0).getFromlatitude() : "29.974490"%>,
                                                    lng: <%= maJobses.size() > 0 ? maJobses.get(0).getFromlongitude() : "-95.817450"%>,
                                                    zoom: 11
                                                });
                                                //Loding address
    <% for (JobPojo maJobs : maJobses) {%>
                                                map.addMarker({
                                                    lat: <%= maJobs.getFromlatitude()%>,
                                                    lng: <%= maJobs.getFromlongitude()%>,
                                                    title: '<%= maJobs.getJobname()%>',
                                                    infoWindow: {
                                                        content: '<p><%= maJobs.getJobname()%></p>'
                                                    }
                                                });
    <%}%>
    <% for (JobPojo maJobs : maJobses) {%>
                                                map.addMarker({
                                                    lat: <%= maJobs.getTolatitude()%>,
                                                    lng: <%= maJobs.getTolongitude()%>,
                                                    title: '<%= maJobs.getJobname()%>',
                                                    icon: 'http://maps.google.com/mapfiles/ms/icons/green-dot.png',
                                                    infoWindow: {
                                                        content: '<p><%= maJobs.getJobname()%></p>'
                                                    }
                                                });
    <%}%>

//Loding address
    <%  for (JobPojo maJobs2 : maJobses) {%>
                                                map.addMarker({
                                                    lat: <%= maJobs2.getFromlatitude()%>,
                                                    lng: <%= maJobs2.getFromlongitude()%>,
                                                    title: '<%= maJobs2.getJobname()%>',
                                                    infoWindow: {
                                                        content: "<p>Job Name: <%= maJobs2.getJobname()%> </br> Total Driver: <%= maJobs2.getDrivercount()%> </br>Job Number: <%= maJobs2.getJobnumber()%></p>"
                                                    }
                                                });
    <%}%>
//Dumping address
    <% for (JobPojo maJobs : maJobses) {%>
                                                map.addMarker({
                                                    lat: <%= maJobs.getTolatitude()%>,
                                                    lng: <%= maJobs.getTolongitude()%>,
                                                    title: '<%= maJobs.getJobname()%>',
                                                    icon: 'http://maps.google.com/mapfiles/ms/icons/green-dot.png',
                                                    infoWindow: {
                                                        content: '<p>Job Name:<%= maJobs.getJobname()%></br> Total Driver: <%= maJobs.getDrivercount()%> </br>Job Number: <%= maJobs.getJobnumber()%></p>'
                                                    }
                                                });
    <%}%>


                                            });



//    ---------- chart
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

</script>
<script src="<%=request.getContextPath()%>/assets-new/app-assets/vendors/js/tables/datatable/vfs_fonts.js"></script>
<script src="<%=request.getContextPath()%>/assets-new/app-assets/vendors/js/tables/datatable/datatables.min.js"></script>
<script src="<%=request.getContextPath()%>/assets-new/app-assets/vendors/js/tables/datatable/datatables.buttons.min.js"></script>
<script src="<%=request.getContextPath()%>/assets-new/app-assets/vendors/js/tables/datatable/buttons.html5.min.js"></script>
<script src="<%=request.getContextPath()%>/assets-new/app-assets/vendors/js/tables/datatable/buttons.print.min.js"></script>
<script src="<%=request.getContextPath()%>/assets-new/app-assets/vendors/js/tables/datatable/buttons.bootstrap.min.js"></script>
<script src="<%=request.getContextPath()%>/assets-new/app-assets/vendors/js/tables/datatable/datatables.bootstrap4.min.js"></script>
<script src="<%=request.getContextPath()%>/assets-new/app-assets/js/scripts/datatables/datatable.js"></script>
