<%@page import="com.wmtrucking.pojo.JobPojo"%>
<%@page import="com.wmtrucking.entities.MaJobs"%>
<%@page import="com.wmtrucking.utils.CheckInput"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="com.wmtrucking.utils.DateUtils"%>


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
    .tooltip-wrap {
        position: relative;
    }
    .tooltip-wrap .tooltip-content {
        display: none;
        position: absolute;
        bottom: -49%;
        left: -90%;
        right: -85%;
        background-color: #fff;
        padding: 0.5em;
    }
    .tooltip-wrap:hover .tooltip-content {
        display: block;
        background-color: #ffe6e6;
        border-radius: 15px !important;
    }
</style>
<jsp:include page="../Template/header.jsp"></jsp:include>
    <!-- Page container -->
    <div class="content-wrapper">
        <div class="content-header row">
            <div class="content-header-left col-md-9 col-12 mb-2">
                <div class="row breadcrumbs-top">
                    <div class="col-12">
                        <h2 class="content-header-title float-left mb-0">Job </h2>
                        <div class="breadcrumb-wrapper col-12">
                            <ol class="breadcrumb">
                                <li class="breadcrumb-item"><a href="<%=request.getContextPath()%>/Dashboard/Dashboard">Home</a>
                            </li>
                            <li class="breadcrumb-item active"><a href="#">Job List</a></li>
                        </ol>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <div class="content-body">
        <!-- Zero configuration table -->
        <section id="horizontal-vertical">
            <div class="row">
                <div class="col-12">

                    <div class="card">
                        <div class="card-header">
                            <h4 class="card-title">Job - List</h4>
                            <div class="bg-default content-box text-right pad20A mrg25T">
                                <a class="btn bg-gradient-primary" href="<%=request.getContextPath()%>/job/Create">
                                    <i class="fa fa-plus"></i> Create New Job</a>

                                <a class="btn bg-gradient-primary" href="<%=request.getContextPath()%>/job/archiveList">
                                    <i class="fa fa-archive"></i> Archive Job</a>
                            </div>
                        </div>
                        <div class="card-content">
                            <div class="card-body card-dashboard">
                                <%if (request.getParameter("m") != null) {%>
                                <%if (request.getParameter("m").equals("c")) {%>
                                <div class="alert alert-success">
                                    <button class="close" data-dismiss="alert"><span>x</span></button>
                                    Job has been successfully created.
                                </div>
                                <%}
                                    if (request.getParameter("m").equals("assign")) {%>
                                <div class="alert alert-success">
                                    <button class="close" data-dismiss="alert"><span>x</span></button>
                                    Driver has been successfully Assign.
                                </div>
                                <%} else if (request.getParameter("m").equals("edit")) {%>
                                <div class="alert alert-success">
                                    <button class="close" data-dismiss="alert"><span>x</span></button>
                                    Job has been successfully updated.
                                </div>
                                <%} else if (request.getParameter("m").equals("delete")) {%>
                                <div class="alert alert-success">
                                    <button class="close" data-dismiss="alert"><span>x</span></button>
                                    Job has been successfully deleted.
                                </div>
                                <%} else if (request.getParameter("m").equals("notDelete")) {%>
                                <div class="alert alert-danger">
                                    <button class="close" data-dismiss="alert"><span>x</span></button>
                                    This job is already started. You can not delete.
                                </div>
                                <%} else if (request.getParameter("m").equals("n")) {%>
                                <div class="alert alert-success">
                                    <button class="close" data-dismiss="alert"><span>x</span></button>
                                    Record not found
                                </div>
                                <%} else if (request.getParameter("m").equals("notAssign")) {%>
                                <div class="alert alert-danger">
                                    <button class="close" data-dismiss="alert"><span>x</span></button>
                                    This Job is already started 
                                </div>
                                <%} else if (request.getParameter("m").equals("completed")) {%>
                                <div class="alert alert-danger">
                                    <button class="close" data-dismiss="alert"><span>x</span></button>
                                    This Job has Completed
                                </div>
                                <%} else if (request.getParameter("m").equals("archived")) {%>
                                <div class="alert alert-success">
                                    <button class="close" data-dismiss="alert"><span>x</span></button>
                                    This Job has Archived successfully 
                                </div>
                                <%}%>
                                <%}%>
                                <div class="table-responsive">
                                    <table class="table zero-configuration">
                                        <thead>
                                            <tr>

                                                <th>Job Number</th>
                                                <th>Job Name</th>                                 
                                                <th>Job Date</th>  
                                                <th> Total Dumps</th>
                                                <th>Pickup Dumps</th>
                                                <th>Completed Dumps</th>
                                                <th width="50%">Job Status</th>  
                                                <th> Total Driver </th>
                                                <th >Driver Names </th>
                                                <th>Actions</th>
                                            </tr>
                                        </thead>
                                        <tbody>
                                            <%
                                                CheckInput checkInput = new CheckInput();
                                                List<JobPojo> majobs = (List<JobPojo>) request.getAttribute("maJobs");
                                                if (!majobs.isEmpty()) {
                                                    for (JobPojo majob : majobs) {
                                            %>
                                            <tr>
                                                <td><%=checkInput.checkValue(majob.getJobnumber())%></td>
                                                <td><%=checkInput.checkValue(majob.getJobname())%></td>
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
                                                <td style="text-align: center"><%=checkInput.checkValue(majob.getDrivercount())%></td>
                                                <!--                                                                                                <td>
                                                                                                
                                                <% if (majob.getDrivername() != null) {
                                                        String[] driver = majob.getDrivername().split(",");
                                                        for (int i = 0; i < driver.length; i++) {
                                                %>
                                                <span class="label <%= i % 2 == 0 ? "label-orange" : "label-pur"%>  " ><%= driver[i]%></span>
                                                <%}
                                                    }%>
                                            </td>                                            -->

                                                <td> 
                                                    <%if (majob.getDrivercount() > 0) {%>
                                                    <div class="tooltip-wrap">

                                                        <i class="feather icon-users "></i>                                                  
                                                        <div class="tooltip-content">
                                                            <p>  <% if (majob.getDrivername() != null) {
                                                                    String[] driver = majob.getDrivername().split(","); %>
                                                           
                                                            <table>
                                                                <%   for (int i = 0; i < driver.length; i++) {
                                                                %>
                                                                <tr><td> <span class="label label-orange " ><%= driver[i]%></span></td></tr>
                                                                <%}%>
                                                            </table>
                                                            <%}%></p>
                                                        </div> 
                                                    </div><%}%></td>
                                                <td>
                                                    <div class="btn-group">
                                                        <div class="dropdown">
                                                            <button class="btn btn-flat-primary dropdown-toggle mr-1 mb-1" type="button" id="dropdownMenuButton100" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                                                                <i class="feather icon-menu"></i>
                                                            </button>
                                                            <div class="dropdown-menu" aria-labelledby="dropdownMenuButton100">

                                                                <a class="dropdown-item"  style="font-size: 15px;" href="<%=request.getContextPath()%>/job/view/<%=majob.getId()%>?flag=false">
                                                                    <i class="feather icon-eye"></i><span>View</span>
                                                                </a>
                                                                <%if (!majob.getTransectionstatus().equals("0")) {%>
                                                                <a class="dropdown-item"  style="font-size: 15px;" href="<%=request.getContextPath()%>/job/assignJobDr/<%=majob.getId()%>">
                                                                    <i class="feather icon-user "></i><span>Assign Driver</span>
                                                                </a> 

                                                                <%if (!majob.getTransectionstatus().equals("3")) {%>
                                                                <a class="dropdown-item" onclick="changeStatus('Delete', '<%=majob.getId()%>')">
                                                                    <i class="feather icon-trash"></i> <span>Delete</span>
                                                                </a>
                                                                <%}
                                                                } else {%>
                                                                <a class="dropdown-item"  style="font-size: 15px;" href="<%=request.getContextPath()%>/job/assignJobDr/<%=majob.getId()%>?flag=complete">
                                                                    <i class="feather icon-user "></i><span>View Assign Driver</span>
                                                                </a> 
                                                                <a class="dropdown-item"  style="font-size: 15px;" href="<%=request.getContextPath()%>/invoice/list/<%=majob.getId()%>">
                                                                    <i class="feather icon-eye "></i><span>Show Invoice</span>
                                                                </a>
                                                                     <a class="dropdown-item"  onclick="archive('Archive', '<%=majob.getId()%>')">
                                                                    <i class="feather icon-archive "></i> <span>Archive</span>
                                                                </a>
                                                                <%}%>
                                                                <a class="dropdown-item" href="<%=request.getContextPath()%>/job/edit/<%=majob.getId()%>">
                                                                    <i class="feather icon-edit"></i> <span>Edit</span>
                                                                </a>
                                                               

                                                            </div>
                                                        </div>
                                                    </div>
                                                </td>
                                            </tr>
                                            <%   }
                                                }%>
                                        </tbody>

                                    </table>
                                    <p id="info" style="display: none">Text to popup</p>

                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </section>

    </div>
</div>
<jsp:include page="../Template/pageEnd.jsp"></jsp:include>    

<jsp:include page="../Template/footer.jsp"></jsp:include>

    <script src="<%=request.getContextPath()%>/assets-new/app-assets/vendors/js/tables/datatable/vfs_fonts.js"></script>
<script src="<%=request.getContextPath()%>/assets-new/app-assets/vendors/js/tables/datatable/datatables.min.js"></script>
<script src="<%=request.getContextPath()%>/assets-new/app-assets/vendors/js/tables/datatable/datatables.buttons.min.js"></script>
<script src="<%=request.getContextPath()%>/assets-new/app-assets/vendors/js/tables/datatable/buttons.html5.min.js"></script>
<script src="<%=request.getContextPath()%>/assets-new/app-assets/vendors/js/tables/datatable/buttons.print.min.js"></script>
<script src="<%=request.getContextPath()%>/assets-new/app-assets/vendors/js/tables/datatable/buttons.bootstrap.min.js"></script>
<script src="<%=request.getContextPath()%>/assets-new/app-assets/vendors/js/tables/datatable/datatables.bootstrap4.min.js"></script>
<script src="<%=request.getContextPath()%>/assets-new/app-assets/js/scripts/datatables/datatable.js"></script>

<script src="<%=request.getContextPath()%>/assets-new/app-assets/vendors/js/extensions/sweetalert2.all.min.js"></script>

<script>

                                                                    $(document).ready(function () {
                                                                        var e = document.getElementById('icon');
                                                                        e.onmouseover = function () {
                                                                            document.getElementById('info').style.display = 'block';
                                                                        }
                                                                        e.onmouseout = function () {
                                                                            document.getElementById('info').style.display = 'none';
                                                                        }
                                                                    });

                                                                    function archive(status, id) {
                                                                        Swal.fire({
                                                                            title: "Are You Sure to " + status + " This Job?",
                                                                            text: "You Are Going to " + status + " Job",
                                                                            type: 'warning',
                                                                            showCancelButton: true,
                                                                            confirmButtonColor: '#3085d6',
                                                                            cancelButtonColor: '#d33',
                                                                            confirmButtonText: "Yes, " + status + " it!",
                                                                            confirmButtonClass: 'btn btn-primary',
                                                                            cancelButtonClass: 'btn btn-danger ml-1',
                                                                            buttonsStyling: false,
                                                                        }).then(function (result) {
                                                                            if (result.value) {
                                                                                window.location = "<%=request.getContextPath()%>/job/archive/" + id;
                                                                            }
                                                                        })
                                                                    }
                                                                    function changeStatus(status, id) {
                                                                        Swal.fire({
                                                                            title: "Are You Sure to " + status + " This Job?",
                                                                            text: "You Are Going to " + status + " Job",
                                                                            type: 'warning',
                                                                            showCancelButton: true,
                                                                            confirmButtonColor: '#3085d6',
                                                                            cancelButtonColor: '#d33',
                                                                            confirmButtonText: "Yes, " + status + " it!",
                                                                            confirmButtonClass: 'btn btn-primary',
                                                                            cancelButtonClass: 'btn btn-danger ml-1',
                                                                            buttonsStyling: false,
                                                                        }).then(function (result) {
                                                                            if (result.value) {
                                                                                window.location = "<%=request.getContextPath()%>/job/delete/" + id;


                                                                            }
                                                                        })
                                                                    }
</script>
