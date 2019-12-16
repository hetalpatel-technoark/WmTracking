<%@page import="java.util.Iterator"%>
<%@page import="com.wmtrucking.utils.CheckInput"%>
<%@page import="com.wmtrucking.entities.MaCustomer"%>
<%@page import="java.util.List"%>
<jsp:include page="../Template/header.jsp"></jsp:include>

    <div class="content-wrapper">
        <div class="content-header row">
            <div class="content-header-left col-md-9 col-12 mb-2">
                <div class="row breadcrumbs-top">
                    <div class="col-12">
                        <h2 class="content-header-title float-left mb-0">Customers</h2>
                        <div class="breadcrumb-wrapper col-12">
                            <ol class="breadcrumb">
                                <li class="breadcrumb-item"><a href="<%= request.getContextPath()%>/Dashboard/Dashboard">Home</a>
                            </li>
                            <li class="breadcrumb-item"><a href="<%= request.getContextPath()%>/customer/customerList">Customers</a>
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
        MaCustomer maCustomer = null;
                        if (request.getAttribute("maCustomer") != null) {
                            maCustomer = (MaCustomer) request.getAttribute("maCustomer");
                        }
                        CheckInput checkInput = new CheckInput();
                    %>

                    <div class="card">
                        <div class="card-content">
                            <div class="card-body">
                                <div class="form-body">
                                    <div class="row">
                                        <div class="col-6">
                                            <div class="form-group row">
                                                <div class="col-md-4">
                                                    <span>First Name*</span>
                                                </div>
                                                <div class="col-md-8">
                                                    <input readonly  type="text" class="form-control" name="fname" value="<%=checkInput.checkValueEdit(maCustomer.getFirstname(),request.getParameter("fname"))%>" placeholder="First Name">
                                                </div>
                                            </div>
                                            <div class="form-group row">
                                                <div class="col-md-4">
                                                    <span>Middle Name*</span>
                                                </div>
                                                <div class="col-md-8">
                                                    <input readonly type="text" class="form-control" name="mname" value="<%=checkInput.checkValueEdit (maCustomer.getMiddlename(),request.getParameter("mname"))%>" placeholder="Middle Name">
                                                </div>
                                            </div>
                                            <div class="form-group row">
                                                <div class="col-md-4">
                                                    <span>Last Name*</span>
                                                </div>
                                                <div class="col-md-8">
                                                    <input readonly type="text" class="form-control" name="lname" value="<%=checkInput.checkValueEdit (maCustomer.getLastname(),request.getParameter("lname"))%>" placeholder="Last Name">
                                                </div>
                                            </div>


                                            <div class="form-group row">
                                                <div class="col-md-4">
                                                    <span>Company Name</span>
                                                </div>
                                                <div class="col-md-8">
                                                    <input readonly type="text" class="form-control" name="cmpname" value="<%=checkInput.checkValueEdit (maCustomer.getCompanyname(),request.getParameter("cmpname"))%>" placeholder="Company name">
                                                </div>
                                            </div>

                                            <div class="form-group row">
                                                <div class="col-md-4">
                                                    <span>Phone Number</span>
                                                </div>
                                                <div class="col-md-8">
                                                    <input readonly type="text" readonly="" class="form-control" name="phone" value="<%=checkInput.checkValueEdit (maCustomer.getPhone(),request.getParameter("phone"))%>" placeholder="Phone Number">
                                                </div>
                                            </div>
                                            <div class="form-group row">
                                                <div class="col-md-4">
                                                    <span>Email</span>
                                                </div>
                                                <div class="col-md-8">
                                                    <input readonly type="text" readonly=""  class="form-control" name="email" value="<%=checkInput.checkValueEdit (maCustomer.getEmail(),request.getParameter("email"))%>" placeholder="Email">
                                                </div>
                                            </div>
                                            <div class="form-group row">
                                                <div class="col-md-4">
                                                    <span>Status</span>
                                                </div>
                                                <div class="col-md-8">
                                                    <select disabled="" class="form-control select-class" name="status" required>
                                                        <option <%= maCustomer.getStatus().equals("Active")?"selected":"" %> value="Active">Active</option>
                                                        <option <%= maCustomer.getStatus().equals("Inactive")?"selected":"" %> value="Inactive">Inactive</option>
                                                    </select>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="col-6">
                                            <div class="form-group row">
                                                <div class="col-md-4">
                                                    <span>Address 1</span>
                                                </div>
                                                <div class="col-md-8">
                                                    <input readonly type="text" class="form-control" name="add1" value="<%=checkInput.checkValueEdit (maCustomer.getAddress1(),request.getParameter("add1"))%>" placeholder="Address 1">
                                                </div>
                                            </div>
<!--                                            <div class="form-group row">
                                                <div class="col-md-4">
                                                    <span>Address 2</span>
                                                </div>
                                                <div class="col-md-8">
                                                    <input readonly type="text" class="form-control" name="add2" value="<%=checkInput.checkValueEdit (maCustomer.getAddress2(),request.getParameter("add2"))%>" placeholder="Address 2">
                                                </div>
                                            </div>
                                            <div class="form-group row">
                                                <div class="col-md-4">
                                                    <span>Address 3</span>
                                                </div>
                                                <div class="col-md-8">
                                                    <input readonly type="text" class="form-control" name="add3" value="<%=checkInput.checkValueEdit (maCustomer.getAddress3(),request.getParameter("add3"))%>" placeholder="Address 3">
                                                </div>
                                            </div>-->


                                            <div class="form-group row">
                                                <div class="col-md-4">
                                                    <span>City</span>
                                                </div>
                                                <div class="col-md-8">
                                                    <input readonly type="text" class="form-control" name="city" value="<%=checkInput.checkValueEdit (maCustomer.getCity(),request.getParameter("city"))%>" placeholder="City">
                                                </div>
                                            </div>
                                            <div class="form-group row">
                                                <div class="col-md-4">
                                                    <span>State</span>
                                                </div>
                                                <div class="col-md-8">
                                                    <input readonly type="text" class="form-control" name="state" value="<%=checkInput.checkValueEdit (maCustomer.getState(),request.getParameter("state"))%>" placeholder="State">
                                                </div>
                                            </div>
<!--                                            <div class="form-group row">
                                                <div class="col-md-4">
                                                    <span>Country</span>
                                                </div>
                                                <div class="col-md-8">
                                                    <input readonly type="text" class="form-control" name="country" value="<%=checkInput.checkValueEdit (maCustomer.getCountry(),request.getParameter("country"))%>" placeholder="Country">
                                                </div>
                                            </div>-->
                                            <div class="form-group row">
                                                <div class="col-md-4">
                                                    <span>Zipcode</span>
                                                </div>
                                                <div class="col-md-8">
                                                    <input readonly type="text" class="form-control" name="pin" value="<%=checkInput.checkValueEdit (maCustomer.getPincode(),request.getParameter("pin"))%>" placeholder="Zipcode">
                                                </div>
                                            </div>
                                        </div>
                                        <div class="col-md-8 offset-md-4">
                                            <a href="<%= request.getContextPath()%>/customer/customerList" class="btn btn-danger mr-1 mb-1 waves-effect waves-light">Cancel</a>
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