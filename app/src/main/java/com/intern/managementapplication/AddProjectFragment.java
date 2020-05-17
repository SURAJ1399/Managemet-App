package com.intern.managementapplication;

import android.app.AlertDialog;
import android.content.ContentResolver;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.provider.MediaStore;
import android.provider.Settings;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.snackbar.Snackbar;
import com.shrikanthravi.collapsiblecalendarview.data.Day;
import com.shrikanthravi.collapsiblecalendarview.widget.CollapsibleCalendar;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link AddProjectFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AddProjectFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private static final int RESULT_OK = -1;
    View view;
    EditText pname,pbudget,cnumber,cname,date;
    Uri postimageuri;
   EditText imageurl;
    Button choose1,choose2;
    RadioGroup radioGroup;
    String encoded_image;
    LinearLayout root;
    Bitmap bitmap;
    RadioButton radioButton;
    String category="Website";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public AddProjectFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment AddProjectFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static AddProjectFragment newInstance(String param1, String param2) {
        AddProjectFragment fragment = new AddProjectFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View view=inflater.inflate(R.layout.fragment_add_project, container, false);
        date=view.findViewById(R.id.date);
        cname=view.findViewById(R.id.cname);
        pname=view.findViewById(R.id.pname);
        pbudget=view.findViewById(R.id.pbudget);
        cnumber=view.findViewById(R.id.cnumber);
        root=view.findViewById(R.id.root);
        imageurl=view.findViewById(R.id.imageurl);
        final Api api=new Api();

radioGroup=view.findViewById(R.id.radiogp);
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                // find which radio button is selected
                if(checkedId == R.id.web) {
                    category="Website";
                } else if(checkedId == R.id.android) {
                    category="Android";
                } else {
                   category="Others";
                }
            }

        });
//        view.findViewById(R.id.choose2).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent();
//                intent.setType("image/*");
//                intent.setAction(Intent.ACTION_GET_CONTENT);
//                startActivityForResult(Intent.createChooser(intent, "Select Picture"), 1);
//
//
//
//            }
//        });

        view.findViewById(R.id.choose1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder alertDialog = new AlertDialog.Builder(getActivity());
                // alertDialog.setTitle("ADD ROOM");
                //   alertDialog.setMessage(" PLZ ADD Room details");

                LayoutInflater inflater = LayoutInflater.from(getActivity());
                final View date_layout = inflater.inflate(R.layout.layout_date, null);

                alertDialog.setView(date_layout);
                final CollapsibleCalendar collapsibleCalendar = date_layout.findViewById(R.id.calendarView);
                collapsibleCalendar.setCalendarListener(new CollapsibleCalendar.CalendarListener() {
                    @Override
                    public void onDaySelect() {
                        Day day = collapsibleCalendar.getSelectedDay();
                        Log.i(getClass().getName(), "Selected Day: " + day.getYear() + "/" + (day.getMonth() + 1) + "/" + day.getDay());
                        ///   Toast.makeText(GameActivity.this, , Toast.LENGTH_SHORT).show();
                        date.setText(day.getDay() + "-" + (day.getMonth() + 1) + "-" + day.getYear());
                    }

                    @Override
                    public void onItemClick(View view) {

                    }

                    @Override
                    public void onDataUpdate() {

                    }

                    @Override
                    public void onMonthChange() {

                    }

                    @Override
                    public void onWeekChange(int i) {

                    }
                });

                alertDialog.show();

            }
        });

        view.findViewById(R.id.addproject).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if(pname.getText().toString().equals(""))
                {
                    // Toast.makeText(getActivity(), "Enter Username", Toast.LENGTH_SHORT).show();
                    Snackbar snackbar = Snackbar
                            .make(root, "Enter Project Name", Snackbar.LENGTH_LONG);
                    snackbar.show();
                }
                else if(pbudget.getText().toString().equals(""))
                {
                    // Toast.makeText(getActivity(), "Enter Username", Toast.LENGTH_SHORT).show();
                    Snackbar snackbar = Snackbar
                            .make(root, "Enter Project Buget", Snackbar.LENGTH_LONG);
                    snackbar.show();
                }
                else if(cnumber.getText().toString().equals(""))
                {
                    // Toast.makeText(getActivity(), "Enter Username", Toast.LENGTH_SHORT).show();
                    Snackbar snackbar = Snackbar
                            .make(root, "Enter Client Number", Snackbar.LENGTH_LONG);
                    snackbar.show();
                }
                else {
                  //  Toast.makeText(getActivity(), "Clicked", Toast.LENGTH_SHORT).show();
//
                    StringRequest request=new StringRequest(Request.Method.POST, api.addproject, new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            Toast.makeText(getActivity(), ""+response, Toast.LENGTH_SHORT).show();
                        }
                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Toast.makeText(getActivity(), ""+error.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }


                    ){

                        @Override
                        protected Map<String, String> getParams() throws AuthFailureError {
                            Map<String,String>params=new HashMap<>();
                            params.put("pname",pname.getText().toString());
                            params.put("pbudget",pbudget.getText().toString());
                            params.put("cnumber",cnumber.getText().toString());
                            params.put("cname",cname.getText().toString());
                            params.put("category",category);
                            params.put("date",date.getText().toString());
                           /// params.put("paid","0");
                           // params.put("image",imagestore(bitmap));
                           // params.put("cnumber",cnumber.getText().toString());


                            return params;
                        }
                    };

                    RequestQueue requestQueue= Volley.newRequestQueue(getActivity());
                    requestQueue.add(request);
                }
            }
        });


        return  view;
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data){
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1 && resultCode == RESULT_OK && data != null && data.getData() != null) {
            postimageuri = data.getData();
            try {

                bitmap=MediaStore.Images.Media.getBitmap(getContext().getContentResolver(),postimageuri);
                imageurl.setText("Sssf"+postimageuri+"");
                imagestore(bitmap);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

            //  Bitmap bitmap = MediaStore.Images.Media.getBitmap( postimageuri);
            //  addimage.setImageBitmap(bitmap);


        }
    }

    public String imagestore(Bitmap bitmap) {

//        Toast.makeText(getActivity(), "Enter", Toast.LENGTH_SHORT).show();
        ByteArrayOutputStream stream=new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG,100,stream);
        byte[] imagebytes=stream.toByteArray();
        encoded_image=Base64.encodeToString(imagebytes, Base64.DEFAULT);
      //  Toast.makeText(getContext(), "fdd"+encoded_image, Toast.LENGTH_SHORT).show();
        Log.e("info", encoded_image);
        return  encoded_image;


    }
}

