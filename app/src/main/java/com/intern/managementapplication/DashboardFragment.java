package com.intern.managementapplication;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link DashboardFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DashboardFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    RecyclerView recyclerView;
    DashboardAdapter dashboardAdapter;
    DashboardModel dashboardModelclass;
    public List<DashboardModel>dashboardModel;
    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
Api api=new Api();
    public DashboardFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment DashboardFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static DashboardFragment newInstance(String param1, String param2) {
        DashboardFragment fragment = new DashboardFragment();
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
      View view= inflater.inflate(R.layout.fragment_dashboard, container, false);

        dashboardModel = new ArrayList<>();
        recyclerView=view.findViewById(R.id.dashboardRecycler);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());

        recyclerView.setLayoutManager(layoutManager);



    dashboardAdapter = new DashboardAdapter(dashboardModel,getContext());
        recyclerView.setAdapter(dashboardAdapter);
        retrievedata();
        



      return  view;
    }

    private void retrievedata() {
        StringRequest request=new StringRequest(Request.Method.POST, api.retreive, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                dashboardModel.clear();
                try {
                    JSONObject jsonObject=new JSONObject(response);
                    Gson gson = new Gson();

//                    DashboardModel dashboardModell= gson.fromJson(jsonObject.getJSONObject("data").toString(), DashboardModel.class);
////                    dashboardModel.add(dashboardModell);
//                    dashboardAdapter.notifyDataSetChanged();
                    String success =jsonObject.getString("success");
                    JSONArray jsonArray=jsonObject.getJSONArray("data");
                    Log.e("state",success);

                    if(success.equals("1")){
                        for(int i=0;i<jsonArray.length();i++)
                        {
                            JSONObject object=jsonArray.getJSONObject(i);
                            String id=object.getString("id");
                            String pname=object.getString("pname");
                            String pbudget=object.getString("pbudget");

                            String cname=object.getString("cname");
                            String cnumber=object.getString("cnumber");
                            String category=object.getString("category");
                            String date=object.getString("date");
                            String releasedon=object.getString("releasedon");
                            String paid=object.getString("paid");

                            Log.e("realsedon",releasedon);


                            dashboardModelclass=new DashboardModel(id,pname,pbudget,cname,cnumber,category,date,paid,releasedon);
                            dashboardModel.add(dashboardModelclass);
                            dashboardAdapter.notifyDataSetChanged();




                        }

                    }


                }catch (JSONException e)
                {
                    e.printStackTrace();
                }

                //Toast.makeText(getActivity(), ""+response, Toast.LENGTH_SHORT).show();


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getActivity(), ""+error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }


        );

        RequestQueue requestQueue= Volley.newRequestQueue(getActivity());
        requestQueue.add(request);
    }
}
