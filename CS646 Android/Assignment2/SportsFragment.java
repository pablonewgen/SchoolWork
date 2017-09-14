package edu.sdsu.cs.cs646.assignment2;

import android.content.res.Resources;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

/**
 * Created by Paul on 2/16/2016.
 */
public class SportsFragment extends Fragment {

    private String [] indiaSportsArray;
    private String [] usaSportsArray;
    private String [] mexicoSportsArray;
    private ArrayAdapter<String> indiaAdapter;
    private ArrayAdapter<String> usaAdapter;
    private ArrayAdapter<String> mexicoAdapter;
    private ListView listView;
    private String selectedText;
    private Communicator communicator;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.countries_fragment_layout, container, false);
        final Resources res = getResources();
        /*indiaAdapter = new ArrayAdapter<String>(getActivity(),
                            android.R.layout.simple_list_item_1, indiaSportsArray);
        indiaSportsArray = res.getStringArray(R.array.India);

        usaAdapter = new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_list_item_1, usaSportsArray);
        usaSportsArray = res.getStringArray(R.array.USA);

        mexicoAdapter = new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_list_item_1, mexicoSportsArray);
        mexicoSportsArray = res.getStringArray(R.array.Mexico);*/


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                selectedText = parent.getItemAtPosition(position).toString();

                if (selectedText.equals("India")) {

                    indiaSportsArray = res.getStringArray(R.array.India);
                    ListView listView = (ListView)view.findViewById(R.id.sports_list);
                    listView.setAdapter(indiaAdapter);

                } else if (selectedText.equals("USA")) {

                    ListView listView = (ListView) view.findViewById(R.id.sports_list);
                    listView.setAdapter(usaAdapter);


                } else if (selectedText.equals("Mexico")) {
                    ListView listView = (ListView) view.findViewById(R.id.sports_list);
                    listView.setAdapter(mexicoAdapter);
                }

            }
        });

        return view;
    }
}

