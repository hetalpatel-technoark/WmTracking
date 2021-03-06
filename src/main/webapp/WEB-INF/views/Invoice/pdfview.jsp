<%@page import="com.wmtrucking.utils.DateUtils"%>
<%@page import="com.wmtrucking.entities.MaInvoice"%>
<!doctype html>
<html>
    <head>
        <meta charset="utf-8"/>
        <title>A simple, clean, and responsive HTML invoice template</title>

        <style>
            .invoice-box {

                margin: auto;
                padding: 1px;
                border: 1px solid #eee;
                box-shadow: 0 0 10px rgba(0, 0, 0, .15);
                font-size: 16px;
                line-height: 24px;
                font-family: 'Helvetica Neue', 'Helvetica', Helvetica, Arial, sans-serif;
            }

            .invoice-box table {
                width: 100%;
                line-height: inherit;
                text-align: left;
            }

            .invoice-box table td {
                padding: 5px;
                vertical-align: top;
            }

            .invoice-box table tr td:nth-child(2) {
                text-align: right;
            }

            .invoice-box table tr.top table td {
                padding-bottom: 20px;
            }

            .invoice-box table tr.top table td.title {
                font-size: 45px;
                line-height: 45px;
                color: #333;
            }

            .invoice-box table tr.information table td {
                padding-bottom: 40px;
            }

            .invoice-box table tr.heading td {
                background: #eee;
                border-bottom: 1px solid #ddd;
                font-weight: bold;
            }

            .invoice-box table tr.details td {
                padding-bottom: 20px;
            }

            .invoice-box table tr.item td{
                border-bottom: 1px solid #eee;
            }

            .invoice-box table tr.item.last td {
                border-bottom: none;
            }

            .invoice-box table tr.total td:nth-child(2) {
                border-top: 2px solid #eee;
                font-weight: bold;
            }

            @media only screen and (max-width: 600px) {
                .invoice-box table tr.top table td {
                    width: 100%;
                    display: block;
                    text-align: center;
                }

                .invoice-box table tr.information table td {
                    width: 100%;
                    display: block;
                    text-align: center;
                }
            }

            /** RTL **/
            .rtl {
                direction: rtl;
                font-family: Tahoma, 'Helvetica Neue', 'Helvetica', Helvetica, Arial, sans-serif;
            }

            .rtl table {
                text-align: right;
            }

            .rtl table tr td:nth-child(2) {
                text-align: left;
            }
        </style>
    </head>

    <body>
        <%
            MaInvoice invoices = (MaInvoice) request.getAttribute("maInvoice");
        %>
        <div class="invoice-box">
            <table cellpadding="0" cellspacing="0">
                <tr class="top">
                    <td colspan="2">
                        <table>
                            <tr>
                                <td class="title" style="text-align: center">
                                    <img src="http://54.171.107.227:8081/assets-new/app-assets/images/CompLogo.jpg" style="width:280px; "/>
                                </td>                                
                            </tr>
                        </table>
                    </td>
                </tr>
                <tr class="top">
                    <td colspan="2">
                        <table>
                            <tr>
                                <td class="title" style="text-align: left">
                                    <b>Invoice</b>
                                </td>
                                <td style="text-align: right">
                                    Invoice #: <%= invoices.getId()%><br/>
                                    Customer PO: <%=invoices.getJobid().getJobnumber()%><br/>
                                    Create Date: <%=new DateUtils().dateWithFormat(invoices.getCreateddate(), "MMMM dd, yyyy")%><br/>

                                </td>
                            </tr>
                        </table>
                    </td>
                </tr>

                <tr class="information" style="border-bottom: 1px solid #555">
                    <td colspan="2">
                        <table>
                            <tr>
                                <td style="text-align: left">
                                    <b> To:</b><br/>
                                    <%=invoices.getToaddress().replace(",", ",<br/>")%>
                                </td> 
                                <td style="text-align: right">
                                    <b> From:</b><br/>
                                    <%=invoices.getFromaddress().replace(",", ",<br/>")%>
                                </td>       
                            </tr>
                            <tr> 

                            </tr>
                        </table>
                    </td>
                </tr>               
                <tr class="heading">                    
                    <td >
                        Description 
                    </td>
                    <td style="width: 20%; text-align:right ">
                        Invoice Amount 
                    </td>
                </tr>
                <tr class="details">                   
                    <td  >
                        <%= invoices.getComments()%>
                    </td>
                    <td style="width: 20%; text-align:right">
                        $ <%= invoices.getAmount()%>
                    </td>
                </tr>   

            </table>
            <hr/>
            <div style="text-align: right;background: #F5F5F5;padding-bottom: 10px;padding-top: 5px"> <b> Total:  $ <%= invoices.getAmount()%></b></div>

        </div>
    </body>
</html>
