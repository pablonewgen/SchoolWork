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
 * Created by Paul on 2/14/2016.
 */
public class CountriesFragment extends Fragment {

    private String [] countriesArray;
    private ArrayAdapter<String> adapter;
    private ListView listView;
    private String selectedText;
    private String none = "None Selected";
    private Communicator communicator;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.countries_fragment_layout, container, false);
        Resources res = getResources();
        countriesArray = res.getStringArray(R.array.Countries);
        adapter = new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_list_item_1, countriesArray);
        final ListView listView = (ListView) view.findViewById(R.id.countries_array);
        listView.setAdapter(adapter);

        selectedText = none;
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                selectedText = parent.getItemAtPosition(position).toString();
                communicator.respond(selectedText);
            }
        });
        return view;
    }
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        communicator = (Communicator) getActivity();
    }
}
