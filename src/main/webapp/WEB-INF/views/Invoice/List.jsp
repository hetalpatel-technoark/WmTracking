<%@page import="com.wmtrucking.entities.MaInvoice"%>
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
</style>
<jsp:include page="../Template/header.jsp"></jsp:include>
    <!-- Page container -->
    <div class="content-wrapper">
        <div class="content-header row">
            <div class="content-header-left col-md-12 col-12 mb-2">
                <div class="row breadcrumbs-top">
                    <div class="col-9">
                        <h2 class="content-header-title float-left mb-0">Invoice </h2>
                        <div class="breadcrumb-wrapper col-12">
                            <ol class="breadcrumb">
                                <li class="breadcrumb-item"><a href="<%=request.getContextPath()%>/Dashboard/Dashboard">Home</a>
                            </li>
                            <li class="breadcrumb-item"><a href="<%=request.getContextPath()%>/job/List">Job</a>
                            </li>
                            <li class="breadcrumb-item active"><a href="#">Invoice List</a></li>
                        </ol>
                    </div>
                </div>
                             <div class="col-3">
                    <div class="bg-default content-box text-right pad20A mrg25T">
                        <a class="btn bg-gradient-primary mr-1 mb-1 waves-effect waves-light" href="javascript:window.history.back();">
                            <i class="fa fa-backward"></i> Back</a>
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
                                <%if (request.getParameter("m") != null) {%>
                                <%if (request.getParameter("m").equals("c")) {%>
                                <div class="alert alert-success">
                                    <button class="close" data-dismiss="alert"><span>x</span></button>
                                    Job has been successfully created.
                                </div>
                                <%}
                                    }%>
                                <div class="table-responsive">
                                    <table class="table zero-configuration">
                                        <thead>
                                            <tr>
                                                <th>Customer PO</th>                                            
                                                <th>Amount </th>
                                                <th >Driver Name</th>
                                                <th>Actions</th>
                                            </tr>
                                        </thead>
                                        <tbody>
                                            <%
                                                CheckInput checkInput = new CheckInput();
                                                List<MaInvoice> maInvoices = (List<MaInvoice>) request.getAttribute("maInvoice");
                                                if (!maInvoices.isEmpty()) {
                                                    for (MaInvoice maInvoice : maInvoices) {
                                            %>
                                            <tr>
                                                <td><%=checkInput.checkValue(maInvoice.getJobid().getJobname())%></td>
                                                <td>$<%=checkInput.checkValue(maInvoice.getAmount())%></td>
                                                <td><%=checkInput.checkValue(maInvoice.getDriverid().getFirstname())%></td>  
                                                <td>
                                                    <div class="btn-group">
                                                        <a href="<%=request.getContextPath()%>/invoice/pdf/<%=maInvoice.getId()%>" target="_blank" class="m-portlet__nav-link btn m-btn m-btn--hover-accent m-btn--icon m-btn--icon-only m-btn--pill" title="Generate PDF">
                                                            <i class="fa fa-file-pdf-o" style="font-size:14px;"></i> 
                                                        </a>
                                                    </div>
                                                </td>
                                            </tr>
                                            <%   }
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
