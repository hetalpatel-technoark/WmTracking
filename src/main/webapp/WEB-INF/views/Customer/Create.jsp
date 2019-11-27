<%@page import="java.util.Iterator"%>
<%@page import="com.wmtrucking.utils.CheckInput"%>

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
                            <li class="breadcrumb-item"><a href="#">Create</a>
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
                    %>

                    <div class="card">
                        <div class="card-content">
                            <div class="card-body">
                                <form method="post"  class="form-horizontal bordered-row" id="user-form" action="<%=request.getContextPath()%>/customer/PostCreate">
                                    <div class="form-body">
                                        <div class="row">
                                            <div class="col-6">
                                                <div class="form-group row">
                                                    <div class="col-md-4">
                                                        <span>First Name*</span>
                                                    </div>
                                                    <div class="col-md-8">
                                                        <input type="text" required="" id="first-name" class="form-control" name="fname" value="<%=checkInput.checkValue(request.getParameter("fname"))%>" placeholder="First Name">
                                                    </div>
                                                </div>
                                                <div class="form-group row">
                                                    <div class="col-md-4">
                                                        <span>Middle Name</span>
                                                    </div>
                                                    <div class="col-md-8">
                                                        <input type="text"  id="first-name" class="form-control" name="mname" value="<%=checkInput.checkValue(request.getParameter("mname"))%>" placeholder="Middle Name">
                                                    </div>
                                                </div>
                                                <div class="form-group row">
                                                    <div class="col-md-4">
                                                        <span>Last Name</span>
                                                    </div>
                                                    <div class="col-md-8">
                                                        <input type="text" id="first-name" class="form-control" name="lname" value="<%=checkInput.checkValue(request.getParameter("lname"))%>" placeholder="Last Name">
                                                    </div>
                                                </div>


                                                <div class="form-group row">
                                                    <div class="col-md-4">
                                                        <span>Company Name</span>
                                                    </div>
                                                    <div class="col-md-8">
                                                        <input type="text" id="first-name" class="form-control" name="cmpname" value="<%=checkInput.checkValue(request.getParameter("cmpname"))%>" placeholder="Company name">
                                                    </div>
                                                </div>

                                                <div class="form-group row">
                                                    <div class="col-md-4">
                                                        <span>phone*</span>
                                                    </div>
                                                    <div class="col-md-8">
                                                        <input type="text" required="" id="first-name" class="form-control" name="phone" value="<%=checkInput.checkValue(request.getParameter("phone"))%>" placeholder="Phone no">
                                                    </div>
                                                </div>
                                                <div class="form-group row">
                                                    <div class="col-md-4">
                                                        <span>email*</span>
                                                    </div>
                                                    <div class="col-md-8">
                                                        <input type="email" required="" id="first-name" class="form-control" name="email" value="<%=checkInput.checkValue(request.getParameter("email"))%>" placeholder="Email">
                                                    </div>
                                                </div>

                                            </div>
                                            <div class="col-6">
                                                <div class="form-group row">
                                                    <div class="col-md-4">
                                                        <span>address1</span>
                                                    </div>
                                                    <div class="col-md-8">
                                                        <input type="text" id="first-name" class="form-control" name="add1" value="<%=checkInput.checkValue(request.getParameter("add1"))%>" placeholder="Address 1">
                                                    </div>
                                                </div>
                                                <div class="form-group row">
                                                    <div class="col-md-4">
                                                        <span>address2</span>
                                                    </div>
                                                    <div class="col-md-8">
                                                        <input type="text" id="first-name" class="form-control" name="add2" value="<%=checkInput.checkValue(request.getParameter("add2"))%>" placeholder="Address 2">
                                                    </div>
                                                </div>

   <div class="form-group row">
                                                    <div class="col-md-4">
                                                        <span>address3</span>
                                                    </div>
                                                    <div class="col-md-8">
                                                        <input type="text" id="first-name" class="form-control" name="add3" value="<%=checkInput.checkValue(request.getParameter("add3"))%>" placeholder="Address 3">
                                                    </div>
                                                </div>


                                                <div class="form-group row">
                                                    <div class="col-md-4">
                                                        <span>city</span>
                                                    </div>
                                                    <div class="col-md-8">
                                                        <input type="text"  id="first-name" class="form-control" name="city" value="<%=checkInput.checkValue(request.getParameter("city"))%>" placeholder="City">
                                                    </div>
                                                </div>
                                                <div class="form-group row">
                                                    <div class="col-md-4">
                                                        <span>state</span>
                                                    </div>
                                                    <div class="col-md-8">
                                                        <input type="text" id="state" class="form-control" name="state" value="<%=checkInput.checkValue(request.getParameter("state"))%>" placeholder="State">
                                                    </div>
                                                </div>
                                                <div class="form-group row">
                                                    <div class="col-md-4">
                                                        <span>Country</span>
                                                    </div>
                                                    <div class="col-md-8">
                                                        <input type="text" id="country" class="form-control" name="country" value="<%=checkInput.checkValue(request.getParameter("country"))%>" placeholder="Country">
                                                    </div>
                                                </div>
                                                <div class="form-group row">
                                                    <div class="col-md-4">
                                                        <span>Pincode</span>
                                                    </div>
                                                    <div class="col-md-8">
                                                        <input type="text"  id="pincode" class="form-control" name="pin" value="<%=checkInput.checkValue(request.getParameter("pin"))%>" placeholder="Pincode">
                                                    </div>
                                                </div>
                                            </div>
                                            <div class="col-md-8 offset-md-4">
                                                <button type="submit" class="btn btn-primary mr-1 mb-1">Save Information</button>
                                                <a href="<%= request.getContextPath()%>/customer/customerList" class="btn btn-danger mr-1 mb-1 waves-effect waves-light">Cancel</a>
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
        <!-- // Basic Horizontal form layout section end -->



    </div>
</div>

<!-- END: Content-->
<jsp:include page="../Template/pageEnd.jsp"></jsp:include>    

<jsp:include page="../Template/footer.jsp"></jsp:include>
