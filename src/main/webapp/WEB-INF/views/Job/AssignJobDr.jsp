<%@page import="com.wmtrucking.entities.MaJobs"%>
<%@page import="com.wmtrucking.utils.DateUtils"%>
<%@page import="java.util.Date"%>
<%@page import="java.util.Iterator"%>
<%@page import="com.wmtrucking.utils.CheckInput"%>
<%@page import="com.wmtrucking.entities.MaCustomer"%>
<%@page import="com.wmtrucking.entities.MaDriver"%>

<%@page import="java.util.List"%>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/assets-new/app-assets/vendors/css/pickers/pickadate/pickadate.css">  
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/assets-new/app-assets/vendors/css/forms/select/select2.min.css">

<jsp:include page="../Template/header.jsp"></jsp:include>
    <div class="content-wrapper">
        <div class="content-header row">
            <div class="content-header-left col-md-9 col-12 mb-2">
                <div class="row breadcrumbs-top">
                    <div class="col-12">
                        <h2 class="content-header-title float-left mb-0">Assign Driver</h2>
                        <div class="breadcrumb-wrapper col-12">
                            <ol class="breadcrumb">
                                <li class="breadcrumb-item"><a href="<%= request.getContextPath()%>/Dashboard/Dashboard">Home</a>
                            </li>
                            <li class="breadcrumb-item"><a href="<%= request.getContextPath()%>/job/List">Job</a>
                            </li>
                            <li class="breadcrumb-item"><a href="#">Assign Driver</a>
                            </li>
                        </ol>
                    </div>
                </div>
            </div>
        </div>        
    </div>
    <div class="content-body">
        <!-- Basic Horizontal form layout section start -->
        <section id="basic-horizontal-layouts">
            <div class="row match-height">
                <div class="col-md-12 col-12">
                    <%
                        if (request.getAttribute("error") != null) {
                            List<String> errors = (List<String>) request.getAttribute("error");
                            if (!errors.isEmpty()) {
                    %>
                    <div class="alert alert-danger">
                        <button class="close" data-dismiss="alert"><span>x</span></button>
                        <%
                                Iterator<String> iterator = errors.iterator();
                                while (iterator.hasNext()) {
                                    out.println("<i class='fa fa-arrow-circle-right'></i> " + iterator.next() + "<br/>");
                                }
                            }
                        %>
                    </div>
                    <%                        }
                        CheckInput checkInput = new CheckInput();

                        MaJobs majob = (MaJobs) request.getAttribute("maJob");

                    %>

                    <div class="card">
                        <div class="card-content">
                            <div class="card-body">
                                <form method="post"  class="form-horizontal bordered-row" id="user-form" action="<%=request.getContextPath()%>/job/PostCreateAssignDrive">
                                    <input type="hidden" name="jobid" value="<%= majob.getId() %>">
                                    <div class="form-body">
                                        <div class="row">
                                            <div class="col-12">
                                                <div class="form-group row">
                                                    <div class="col-md-4">
                                                        <span>Assign Driver *</span>
                                                    </div>
                                                    <div class="col-md-8">
                                                        <select class="select2 form-control"  name="driver" required multiple="multiple">
                                                           <%
                                                                String selectedDriver = (String) request.getAttribute("maJobDrivers");

                                                                List<MaDriver> maDrivers = (List<MaDriver>) request.getAttribute("maDriver");
                                                                for (MaDriver maDriver : maDrivers) {
                                                            %>
                                                            <option <%=selectedDriver != null && selectedDriver.contains(checkInput.checkValueEdit(maDriver.getId(), request.getParameter("driver"))) ? "selected" : ""%>
                                                                value="<%=maDriver.getId()%>"><%= maDriver.getFirstname()%></option>
                                                            <%}%>
                                                        </select>
                                                    </div>
                                                </div> 

                                                <div class="form-group row">
                                                    <div class="col-md-4">
                                                        <span>Job Number</span>
                                                    </div>
                                                    <div class="col-md-8">
                                                        <input type="text" disabled="" class="form-control"  value="<%=checkInput.checkValueEdit(majob.getJobnumber(), request.getParameter("jno"))%>">
                                                    </div>
                                                </div>
                                                <div class="form-group row">
                                                    <div class="col-md-4">
                                                        <span>Job Name</span>
                                                    </div>
                                                    <div class="col-md-8">
                                                        <input type="text" disabled class="form-control"  value="<%=checkInput.checkValueEdit(majob.getJobname(), request.getParameter("jname"))%>" >
                                                    </div>
                                                </div>


                                            </div>
                                            <div class="col-md-8 offset-md-4">
                                                <button type="submit" class="btn btn-primary mr-1 mb-1">Save Information</button>
                                                <a href="<%= request.getContextPath()%>/job/List" class="btn btn-danger mr-1 mb-1 waves-effect waves-light">Cancel</a>
                                            </div>
                                        </div>
                                    </div>

                                </form>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </section>  

    </div>
</div>

<!-- END: Content-->
<jsp:include page="../Template/pageEnd.jsp"></jsp:include>    

<jsp:include page="../Template/footer.jsp"></jsp:include>
<script src="<%=request.getContextPath()%>/assets-new/app-assets/vendors/js/forms/select/select2.full.min.js"></script>


<script src="<%=request.getContextPath()%>/assets-new/app-assets/js/scripts/forms/select/form-select2.js"></script>
