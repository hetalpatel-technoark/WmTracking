<%@page import="com.wmtrucking.pojo.JobPojo"%>
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
</style>
<jsp:include page="../Template/header.jsp"></jsp:include>
    <!-- Page container -->
    <div class="content-wrapper">
        <div class="content-header row">
            <div class="content-header-left col-md-9 col-12 mb-2">
                <div class="row breadcrumbs-top">
                    <div class="col-12">
                        <h2 class="content-header-title float-left mb-0">Dumps List </h2>
                        <div class="breadcrumb-wrapper col-12">
                            <ol class="breadcrumb">
                                <li class="breadcrumb-item"><a href="<%=request.getContextPath()%>/Dashboard/Dashboard">Home</a>
                            </li>
                            <li class="breadcrumb-item active"><a href="#">Dumps List</a></li>
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
                        <div class="card-content">
                            <div class="card-body card-dashboard">                              
                                <div class="table-responsive">
                                    <table class="table zero-configuration">
                                        <thead>
                                            <tr>
                                                <th >Job Number</th>                                             
                                                <th >Job Date</th>  
                                                <th >Job Status</th>                                                
                                                <th >Driver Names </th>
                                                <th>Start time </th>                                                

                                            </tr>
                                        </thead>
                                        <tbody>
                                            <%
                                                CheckInput checkInput = new CheckInput();
                                                List<JobPojo> majobs = (List<JobPojo>) request.getAttribute("jobPojo");
                                               
                                                    for (JobPojo majob : majobs) {
                                            %>
                                            <tr>
                                                <td><%=checkInput.checkValue(majob.getJobnumber())%></td>
                                                <td><%=checkInput.checkValue(majob.getJobdate())%></td>                                                                                          
                                                <% if (majob.getTransectionstatus().equals("0")) {%>
                                                <td><span class="label label-success" >Completed</span></td>
                                                <%} else if (majob.getTransectionstatus().equals("1")) {%>
                                                <td><span class="label label-info " >Open</span></td>
                                                <%} else if (majob.getTransectionstatus().equals("3")) {%>
                                                <td><span class="label label-danger " >Pending</span></td>
                                                <%}%>
                                                <td><%=checkInput.checkValue(majob.getDrivername())%></td>
                                                <td><%=checkInput.checkValue(majob.getStarttime())%></td>
                                                <td><%=checkInput.checkValue(majob.getDrivercount())%></td>

                                            </tr>
                                            <%   }
                                                %>
                                        </tbody>
                                    </table>
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
