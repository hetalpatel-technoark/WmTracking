<%@page import="com.wmtrucking.entities.MaDriver"%>
<%@page import="com.wmtrucking.utils.CheckInput"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>

<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/assets-new/app-assets/vendors/css/vendors.min.css">
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/assets-new/app-assets/vendors/css/tables/datatable/datatables.min.css">

<jsp:include page="../Template/header.jsp"></jsp:include>
    <!-- Page container -->
    <div class="content-wrapper">
        <div class="content-header row">
            <div class="content-header-left col-md-9 col-12 mb-2">
                <div class="row breadcrumbs-top">
                    <div class="col-12">
                        <h2 class="content-header-title float-left mb-0">Driver </h2>
                        <div class="breadcrumb-wrapper col-12">
                            <ol class="breadcrumb">
                                <li class="breadcrumb-item"><a href="<%=request.getContextPath()%>/Dashboard/Dashboard">Home</a>
                            </li>
                            <li class="breadcrumb-item active"><a href="#">Driver List</a></li>
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
                            <h4 class="card-title">Driver - List</h4>
                            <div class="bg-default content-box text-right pad20A mrg25T">
                                <a class="btn bg-gradient-primary mr-1 mb-1 waves-effect waves-light" href="<%=request.getContextPath()%>/driver/Create">
                                    <i class="fa fa-plus"></i> Create New Driver</a>
                            </div>
                        </div>
                        <div class="card-content">

                            <div class="card-body card-dashboard">
                                <%if (request.getParameter("m") != null) {%>
                                <%if (request.getParameter("m").equals("c")) {%>
                                <div class="alert alert-success">
                                    <button class="close" data-dismiss="alert"><span>x</span></button>
                                    Driver has been successfully created.
                                </div>
                                <%} else if (request.getParameter("m").equals("e")) {%>
                                <div class="alert alert-success">
                                    <button class="close" data-dismiss="alert"><span>x</span></button>
                                    Driver has been successfully updated.
                                </div>
                                <%} else if (request.getParameter("m").equals("d")) {%>
                                <div class="alert alert-success">
                                    <button class="close" data-dismiss="alert"><span>x</span></button>
                                    Driver has been successfully deleted.
                                </div>
                                <%} else if (request.getParameter("m").equals("n")) {%>
                                <div class="alert alert-danger">
                                    <button class="close" data-dismiss="alert"><span>x</span></button>
                                    This Driver is assigned in job. For delete, please first remove Driver from job.
                                </div>
                                <%}%>
                                <%}%>

                                <div class="table-responsive">
                                    <table class="table zero-configuration">
                                        <thead>
                                            <tr>
                                                <th>Driver Name</th>
                                                <th>License Plate </th>
                                                <th>Driver License</th>
                                                <th>Mobile</th>
                                                <!--                                                <th>Email</th>-->
                                                <th>Status</th>
                                                <th>Actions</th>
                                            </tr>
                                        </thead>
                                        <tbody>
                                            <%
                                                CheckInput checkInput = new CheckInput();
                                                if (request.getAttribute("maDriver") != null) {

                                                    List<MaDriver> madrivers = (List<MaDriver>) request.getAttribute("maDriver");
                                                    if (!madrivers.isEmpty()) {
                                                        for (MaDriver madriver : madrivers) {
                                                            String name = madriver.getFirstname() + "" + (madriver.getMiddlename() != null ? " " + madriver.getMiddlename() : "") + "" + (madriver.getLastname() != null ? " " + madriver.getLastname() : "");
                                                            // String mobile = ((madriver.getCountrycode() != null && madriver.getCountrycode().equals("")) ? madriver.getCountrycode() + " " : "") + madriver.getMobile();
                                                            String mobile =madriver.getMobile()!=null? madriver.getMobile().replaceFirst("(\\d{3})(\\d{3})(\\d+)", "$1-$2-$3"):"";
//                                                            String input = "1234567890";
//                                                            String number = input.replaceFirst("(\\d{3})(\\d{3})(\\d+)", "($1) $2-$3");
                                                            System.out.println(mobile);
                                            %>
                                            <tr>
                                                <td><%=name%></td>

                                                <td><%=checkInput.checkValue(madriver.getLicensenumber())%></td>
                                                <td><%=checkInput.checkValue(madriver.getDriverlicense())%></td>
<!--                                                <td><%=checkInput.checkValue((madriver.getMobile() != null ? madriver.getMobile() : ""))%></td>-->
                                                <td><%=mobile%></td>
                                                <td><span class="label <%=madriver.getStatus().equals("Active") ? "label-success" : "label-danger"%>"><%=madriver.getStatus()%></span></td>

                                                <td>
                                                    <div class="btn-group">
                                                        <div class="dropdown">
                                                            <button class="btn btn-flat-primary dropdown-toggle mr-1 mb-1" type="button" id="dropdownMenuButton100" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                                                                <i class="feather icon-menu"></i>
                                                            </button>
                                                            <div class="dropdown-menu" aria-labelledby="dropdownMenuButton100">
                                                                <a class="dropdown-item"  style="font-size: 15px;" href="<%=request.getContextPath()%>/driver/view/<%=madriver.getId()%>">
                                                                    <i class="feather icon-eye"></i><span>View</span>
                                                                </a>
                                                                <a class="dropdown-item" href="<%=request.getContextPath()%>/driver/edit/<%=madriver.getId()%>">
                                                                    <i class="feather icon-edit"></i> <span>Edit</span>
                                                                </a>
                                                                <a class="dropdown-item" onclick="changeStatus('Delete', '<%=madriver.getId()%>')">
                                                                    <i class="feather icon-trash"></i> <span>Delete</span>
                                                                </a>

                                                            </div>
                                                        </div>
                                                    </div>

                                                </td>
                                            </tr>
                                            <%}
                                                    }
                                                }%>
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

<script>


                                                                    function changeStatus(status, id) {
                                                                        Swal.fire({
                                                                            title: "Are You Sure to " + status + " This Driver?",
                                                                            text: "You Are Going to " + status + " Driver",
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
                                                                                window.location = "<%=request.getContextPath()%>/driver/delete/" + id;


                                                                            }
                                                                        })
                                                                    }
</script>
