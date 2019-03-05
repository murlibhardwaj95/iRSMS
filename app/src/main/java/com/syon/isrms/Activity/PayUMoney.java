package com.syon.isrms.Activity;

/*
public class PayUMoney extends AppCompatActivity {

    private HashMap<String, String> params = new HashMap<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pay_umoney);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        LayoutInflater mInflater = LayoutInflater.from(this);
        View mCustomView = mInflater.inflate(R.layout.customactionbar, null);
        toolbar.addView(mCustomView);
        ImageView drower = (ImageView) mCustomView.findViewById(R.id.search_imageview);
        drower.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PayUMoney.super.onBackPressed();
            }
        });
        TextView logoicon=(TextView)mCustomView.findViewById(R.id.logoicon);
        logoicon.setText("Payment Gateway");

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(Color.parseColor("#A14D3F"));
        }
        toolbar.setTitleMargin(0,0,0,0);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            toolbar.setPaddingRelative(0,0,0,0);
        }
        toolbar.setPadding(0,0,0,0);

        makePayment();

     */
/*   Intent intent = new Intent(this, MakePaymentActivity.class);
        intent.putExtra(PayUMoney_Constants.ENVIRONMENT, PayUMoney_Constants.ENV_DEV);
        intent.putExtra(PayUMoney_Constants.PARAMS, params);

        startActivityForResult(intent, PayUMoney_Constants.PAYMENT_REQUEST);*//*

    }

    public void makePayment(){
        String phone = "9694534870";
        String productName = "One World";
        String firstName = "mukesh";
        String txnId = "123456789";
        String email="mukrimukesh7729@gmail.com";
        String sUrl = "http://schoolstoday.net/";
        String fUrl = "http://schoolstoday.net/";
        String udf1 = "";
        String udf2 = "";
        String udf3 = "";
        String udf4 = "";
        String udf5 = "";
        boolean isDebug = false;
        String key = "54Ojvrep";
        String merchantId = "5747172" ;

        PayUmoneySdkInitilizer.PaymentParam.Builder builder = new PayUmoneySdkInitilizer.PaymentParam.Builder();


        Log.d("values--","name"+firstName+"phone"+phone+"product name"+productName+"txn id"+txnId+"email"+email+"key"+key+"mar id"+merchantId);

        builder.setAmount(100)
                .setTnxId(txnId)
                .setPhone(phone)
                .setProductName(productName)
                .setFirstName(firstName)
                .setEmail(email)
                .setsUrl(sUrl)
                .setfUrl(fUrl)
                .setUdf1(udf1)
                .setUdf2(udf2)
                .setUdf3(udf3)
                .setUdf4(udf4)
                .setUdf5(udf5)
                .setIsDebug(isDebug)
                .setKey(key)
                .setMerchantId(merchantId);



        PayUmoneySdkInitilizer.PaymentParam paymentParam = builder.build();

//             server side call required to calculate hash with the help of <salt>
//             <salt> is already shared along with merchant <key>
     */
/*        serverCalculatedHash =sha512(key|txnid|amount|productinfo|firstname|email|udf1|udf2|udf3|udf4|udf5|<salt>)

             (e.g.)

             sha512(FCstqb|0nf7|10.0|product_name|piyush|piyush.jain@payu.in||||||MBgjYaFG)

             9f1ce50ba8995e970a23c33e665a990e648df8de3baf64a33e19815acd402275617a16041e421cfa10b7532369f5f12725c7fcf69e8d10da64c59087008590fc
*//*


        // Recommended
        calculateServerSideHashAndInitiatePayment(paymentParam);

//        testing purpose

        //  String salt = "";
        //  String serverCalculatedHash=hashCal(key+"|"+txnId+"|"+amt+"|"+productName+"|"
        //       +firstName+"|"+email+"|"+udf1+"|"+udf2+"|"+udf3+"|"+udf4+"|"+udf5+"|"+"ypOVIfrTWI");

        //  Log.d("hash-----",resHash);

        // paymentParam.setMerchantHash(resHash);

        // PayUmoneySdkInitilizer.startPaymentActivityForResult(getActivity(), paymentParam);

    }

    private void calculateServerSideHashAndInitiatePayment(final PayUmoneySdkInitilizer.PaymentParam paymentParam) {

        // Replace your server side hash generator API URL
        String url = "http://maadurgapublication.com/API/hashkey.php";

        Toast.makeText(PayUMoney.this, "Please wait... Generating hash from server ... ", Toast.LENGTH_LONG).show();
        StringRequest jsonObjectRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);

                    if (jsonObject.has(SdkConstants.STATUS)) {
                        String status = jsonObject.optString(SdkConstants.STATUS);
                        if (status != null || status.equals("1")) {

                            String hash = jsonObject.getString(SdkConstants.RESULT);
                            Log.i("app_activity", "Server calculated Hash :  " + hash);

                            paymentParam.setMerchantHash(hash);

                            PayUmoneySdkInitilizer.startPaymentActivityForResult(PayUMoney.this, paymentParam);
                        } else {
                            Toast.makeText(PayUMoney.this,
                                    jsonObject.getString(SdkConstants.RESULT),
                                    Toast.LENGTH_SHORT).show();
                        }

                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {

                if (error instanceof NoConnectionError) {
                    Toast.makeText(PayUMoney.this,
                            PayUMoney.this.getString(R.string.connect_to_internet),
                            Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(PayUMoney.this,
                            error.getMessage(),
                            Toast.LENGTH_SHORT).show();

                }

            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                return paymentParam.getParams();
            }
        };
        Volley.newRequestQueue(PayUMoney.this).add(jsonObjectRequest);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (requestCode == PayUmoneySdkInitilizer.PAYU_SDK_PAYMENT_REQUEST_CODE) {

            */
/*if(data != null && data.hasExtra("result")){
              String responsePayUmoney = data.getStringExtra("result");
                if(SdkHelper.checkForValidString(responsePayUmoney))
                    showDialogMessage(responsePayUmoney);
            } else {
                showDialogMessage("Unable to get Status of Payment");
            }*//*



            if (resultCode == RESULT_OK) {
                Log.i("Result", "Success - Payment ID : " + data.getStringExtra(SdkConstants.PAYMENT_ID));
                String paymentId = data.getStringExtra(SdkConstants.PAYMENT_ID);
               // SaveTXN("success");
                // showDialogMessage("Payment Success Id : " + paymentId);
            } else if (resultCode == RESULT_CANCELED) {
                Log.i("Result", "failure");
               // SaveTXN("cancelled");
                Toast.makeText(getApplicationContext(),"success",Toast.LENGTH_SHORT).show();
                // showDialogMessage("cancelled");
            } else if (resultCode == PayUmoneySdkInitilizer.RESULT_FAILED) {
                Log.i("app_activity", "failure");

                if (data != null) {
                    if (data.getStringExtra(SdkConstants.RESULT).equals("cancel")) {

                    } else {
                        //SaveTXN("failed");
                        Toast.makeText(getApplicationContext(),"failed",Toast.LENGTH_SHORT).show();
                        // showDialogMessage("failure");
                    }
                }
                //Write your code if there's no result
            } else if (resultCode == PayUmoneySdkInitilizer.RESULT_BACK) {
                Log.i("Result", "User returned without login");
                Toast.makeText(getApplicationContext(),"User_returned_without_login",Toast.LENGTH_SHORT).show();
                //SaveTXN("User_returned_without_login");
                //  showDialogMessage("User returned without login");
            }
        }
    }
*/
/*
    private synchronized void init() {

        params.put(PayUMoney_Constants.KEY, "tBFujg");
        params.put(PayUMoney_Constants.merchantId , "9010575");
        params.put(PayUMoney_Constants.TXN_ID, "1");
        params.put(PayUMoney_Constants.AMOUNT, "100");
        params.put(PayUMoney_Constants.PRODUCT_INFO, "tshirt");
        params.put(PayUMoney_Constants.FIRST_NAME, "hello");
        params.put(PayUMoney_Constants.EMAIL, "email@gmail.com");
        params.put(PayUMoney_Constants.PHONE, "9694534870");
        params.put(PayUMoney_Constants.SURL, "success_url");
        params.put(PayUMoney_Constants.FURL, "failure_url");
        params.put(PayUMoney_Constants.UDF1, "");
        params.put(PayUMoney_Constants.UDF2, "");
        params.put(PayUMoney_Constants.UDF3, "");
        params.put(PayUMoney_Constants.UDF4, "");
        params.put(PayUMoney_Constants.UDF5, "");

        String hash = Utils.generateHash(params, "Gm16qWAh");

        params.put(PayUMoney_Constants.HASH, hash);
        params.put(PayUMoney_Constants.SERVICE_PROVIDER, "payu_paisa");

    }
*//*

  */
/*  @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == PayUMoney_Constants.PAYMENT_REQUEST) {
            if (resultCode == RESULT_OK) {
                Toast.makeText(this, "Payment Success,", Toast.LENGTH_SHORT).show();
            } else if (resultCode == RESULT_CANCELED) {
                Toast.makeText(this, "Payment Failed | Cancelled.", Toast.LENGTH_SHORT).show();
            }
        }
    }*//*

}

*/
