<%@page import="java.util.Date"%>
<%@page import="java.util.Iterator"%>
<%@page import="com.wmtrucking.utils.CheckInput"%>
<%@page import="com.wmtrucking.entities.MaCustomer"%>
<%@page import="com.wmtrucking.entities.MaJobs"%>
<%@page import="com.wmtrucking.entities.MaDriver"%>
<%@page import="com.wmtrucking.utils.DateUtils"%>

<%@page import="java.util.List"%>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/assets-new/app-assets/vendors/css/pickers/pickadate/pickadate.css">  
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/assets-new/app-assets/vendors/css/forms/select/select2.min.css">

<jsp:include page="../Template/header.jsp"></jsp:include>

    <div class="content-wrapper">
        <div class="content-header row">
            <div class="content-header-left col-md-9 col-12 mb-2">
                <div class="row breadcrumbs-top">
                    <div class="col-12">
                        <h2 class="content-header-title float-left mb-0">Job</h2>
                        <div class="breadcrumb-wrapper col-12">
                            <ol class="breadcrumb">
                                <li class="breadcrumb-item"><a href="<%= request.getContextPath()%>/Dashboard/Dashboard">Home</a>
                            </li>
                            <li class="breadcrumb-item"><a href="<%= request.getContextPath()%>/job/List">Job</a>
                            </li>
                            <li class="breadcrumb-item"><a href="#">View</a>
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
                        if (request.getAttribute("errors") != null) {
                            List<String> errors = (List<String>) request.getAttribute("errors");
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
                        DateUtils dateUtils = new DateUtils();
                        MaJobs majob = null;
                        if (request.getAttribute("maJobs") != null) {
                            majob = (MaJobs) request.getAttribute("maJobs");
                        }

                    %>

                    <div class="card">
                        <div class="card-content">
                            <div class="card-body">
                                <input readonly type="hidden" name="id" value="<%=checkInput.checkValue(majob.getId())%>">
                                <div class="form-body">
                                    <div class="row">
                                        <div class="col-6">
                                            <div class="form-group row">
                                                <div class="col-md-4">
                                                    <span>Customer /Company</span>
                                                </div>
                                                <div class="col-md-8">
                                                    <select disabled class="select2 form-control"  name="customer" required multiple="multiple">
                                                        <%
                                                            String selectedCustomer = (String) request.getAttribute("majobcustomer");
                                                            List<MaCustomer> maCustomers = (List<MaCustomer>) request.getAttribute("maCustomer");
                                                            for (MaCustomer maCustomer : maCustomers) {
                                                        %>
                                                        <option disabled=""  <%=selectedCustomer != null && selectedCustomer.contains(checkInput.checkValueEdit(maCustomer.getId(), request.getParameter("customer"))) ? "selected" : ""%>
                                                                value="<%=maCustomer.getId()%>"><%= maCustomer.getFirstname()%></option>
                                                        <%                                                                }%>
                                                    </select>
                                                </div>
                                            </div>  

                                            <div class="form-group row">
                                                <div class="col-md-4">
                                                    <span>Total Dumps* </span>
                                                </div>
                                                <div class="col-md-8">
                                                    <input disabled="" type="number" max="1000"  class="form-control" name="count" value="<%=checkInput.checkValueEdit(majob.getTotaljobcount(), request.getParameter("count"))%>"  placeholder="Total Job Dump">
                                                </div>
                                            </div>

                                            <div class="form-group row">
                                                <div class="col-md-4">
                                                    <span>Loading Site Address *</span>
                                                </div>
                                                <div class="col-md-8">
                                                    <input type="text" required="" id="loding" disabled=""  class="form-control" name="lodingAddress" value="<%=checkInput.checkValueEdit(majob.getLodingaddress(), request.getParameter("lodingAddress"))%>"  placeholder="Loading Site Address">
                                                </div>
                                            </div>
                                            <div class="form-group row">
                                                <div class="col-md-4">
                                                    <span>Dumping Site Address *</span>
                                                </div>
                                                <div class="col-md-8">
                                                    <input type="text" required id="dumping" disabled  class="form-control" name="DumpingAddress" value="<%=checkInput.checkValueEdit(majob.getDumpingaddress(), request.getParameter("DumpingAddress"))%>"  placeholder="Dumping Site Address">
                                                </div>
                                            </div>
        <div class="form-group row">
                                                <div class="col-md-4">
                                                    <!--                                                    <span>Select fill</span>-->
                                                </div>
                                                <div class="col-sm-8">
                                                    <div class="col-sm-12" >
                                                        <ul class="list-unstyled mb-0">
                                                            <li class="d-inline-block mr-2">
                                                                <fieldset>
                                                                    <div class="custom-control custom-checkbox">
                                                                        <input disabled type="checkbox" class="custom-control-input" <% if (majob.getSand()) {%>checked<% } %>   value="Sand" name="Sand" id="customCheck1">
                                                                        <label class="custom-control-label" for="customCheck1">Sand </label>
                                                                    </div>
                                                                </fieldset></li>
                                                            <li class="d-inline-block mr-2">
                                                                <fieldset>
                                                                    <div class="custom-control custom-checkbox">
                                                                        <input disabled type="checkbox" class="custom-control-input" <% if (majob.getSelectfill()) {%>checked<% } %>   value="selectfill" name="selectfill" id="customCheck2">
                                                                        <label class="custom-control-label" for="customCheck2">Fill </label>
                                                                    </div>
                                                                </fieldset>
                                                            </li>
                                                            <li class="d-inline-block mr-2">
                                                                <fieldset>
                                                                    <div class="custom-control custom-checkbox">
                                                                        <input disabled readonly type="checkbox" class="custom-control-input" <% if (majob.getHauloff()) {%>checked<% } %> id="customCheck3">
                                                                        <label class="custom-control-label" for="customCheck3">Haul Off </label>
                                                                    </div>
                                                                </fieldset></li>
                                                            <br>
                                                            <li class="d-inline-block mr-2">
                                                                <fieldset>
                                                                    <div class="custom-control custom-checkbox">
                                                                        <input disabled readonly type="checkbox" class="custom-control-input" <% if (majob.getHaulback()) {%>checked<% } %>   id="customCheck4">
                                                                        <label class="custom-control-label" for="customCheck4">Haul Back</label>
                                                                    </div>
                                                                </fieldset></li>
                                                            <li class="d-inline-block mr-2">
                                                                <fieldset>
                                                                    <div class="custom-control custom-checkbox">
                                                                        <input disabled type="checkbox" class="custom-control-input" <% if (majob.getCommon()) {%>checked<% }%>   id="customCheck5">
                                                                        <label class="custom-control-label" for="customCheck5">Common </label>
                                                                    </div>
                                                                </fieldset></li>
                                                            <li class="d-inline-block mr-2">
                                                                <fieldset>
                                                                    <div class="custom-control custom-checkbox">
                                                                        <input disabled type="checkbox" class="custom-control-input" <% if (majob.getHourly() != null && majob.getHourly()) {%>checked<% }%>   id="customCheck6">
                                                                        <label class="custom-control-label" for="customCheck6">Hourly </label>
                                                                    </div>
                                                                </fieldset></li>
                                                        </ul>
                                                    </div> 
                                                </div>
                                            </div>

                                        </div>
                                        <div class="col-6">
                                            <div class="form-group row">
                                                <div class="col-md-4">
                                                    <span>Job Number*</span>
                                                </div>
                                                <div class="col-md-8">
                                                    <input readonly  type="text" class="form-control" name="jno" value="<%=checkInput.checkValueEdit(majob.getJobnumber(), request.getParameter("jno"))%>" placeholder="Job number">
                                                </div>
                                            </div>
                                            <div class="form-group row">
                                                <div class="col-md-4">
                                                    <span>Job Name*</span>
                                                </div>
                                                <div class="col-md-8">
                                                    <input type="text" disabled="" class="form-control" name="jname" value="<%=checkInput.checkValueEdit(majob.getJobname(), request.getParameter("jname"))%>" placeholder="Job Name">
                                                </div>
                                            </div>
                                            <div class="form-group row">
                                                <div class="col-md-4">                                                    
                                                    <span>Job Date *</span></div>
                                                <div class="col-md-8">
                                                    <input readonly type="text" disabled="" id="expirydate" name="jobdate" value="<%=checkInput.checkValueEdit(dateUtils.dateWithFormat(majob.getJobdate(), "MMMM dd, yyyy"), request.getParameter("jobdate"))%>" style="background-color: #F5F5F1" class="form-control pickadate1" placeholder="Job Date">
                                                </div>
                                            </div>

                                    
                                            <div class="form-group row">
                                                <div class="col-md-4">
                                                    <span>Others</span>
                                                </div>
                                                <div class="col-md-8">
                                                    <input type="text" readonly="" id="pincode" class="form-control" name="others" value="<%=checkInput.checkValueEdit(majob.getOther(), request.getParameter("others"))%>" placeholder="Others">
                                                </div>
                                            </div>
                                            <div class="form-group row">
                                                <div class="col-md-4">
                                                    <span>Notes</span>
                                                </div>
                                                <div class="col-md-8">
                                                    <textarea class="form-control" readonly name="notes"><%=checkInput.checkValueEdit(majob.getNotes(), request.getParameter("notes"))%> </textarea>
                                                </div>
                                            </div>
                                            <div class="form-group row">
                                                <div class="col-md-4">                                                    
                                                    <span>Job Created Date </span></div>
                                                <div class="col-md-8">
                                                    <input type="text"  disabled=""  value="<%= new DateUtils().dateWithFormat(new Date(), "MMMM dd, yyyy")%>"  class="form-control " placeholder="Job Date">
                                                </div>
                                            </div>
                                        </div>
                                        <div class="col-md-8 offset-md-4">
                                            <a href="<%= request.getContextPath()%>/job/List" class="btn btn-danger mr-1 mb-1 waves-effect waves-light">Cancel</a>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </section>
        <!-- // Basic Horizontal form layout section end -->



    </div>
</div>

<!-- END: Content-->
<jsp:include page="../Template/pageEnd.jsp"></jsp:include>    

<jsp:include page="../Template/footer.jsp"></jsp:include>
<script src="<%=request.getContextPath()%>/assets-new/app-assets/vendors/js/forms/select/select2.full.min.js"></script>

<script>
    $(document).ready(function () {
        $(".pickadate1").pickadate({
            format: "mm-dd-yyyy"
        });


    });

</script>

<script src="<%=request.getContextPath()%>/assets-new/app-assets/vendors/js/pickers/pickadate/picker.js"></script>
<script src="<%=request.getContextPath()%>/assets-new/app-assets/vendors/js/pickers/pickadate/picker.date.js"></script>
<script src="<%=request.getContextPath()%>/assets-new/app-assets/vendors/js/pickers/pickadate/picker.time.js"></script>
<script src="<%=request.getContextPath()%>/assets-new/app-assets/vendors/js/pickers/pickadate/legacy.js"></script>

<script src="<%=request.getContextPath()%>/assets-new/app-assets/js/scripts/pickers/dateTime/pick-a-datetime.js"></script>
<script src="<%=request.getContextPath()%>/assets-new/app-assets/js/scripts/forms/select/form-select2.js"></script>
