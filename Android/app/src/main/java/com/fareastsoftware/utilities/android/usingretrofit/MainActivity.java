package com.fareastsoftware.utilities.android.usingretrofit;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.fareastsoftware.utilities.android.usingretrofit.databinding.ActivityMainBinding;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private static final String TAG="MainActivity";
    private ActivityMainBinding mBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main);

        // create a ViewModel...
        ViewModelProvider p = new ViewModelProvider(this,
                ViewModelProvider.AndroidViewModelFactory.getInstance(getApplication()));
        MainActivityViewModel vm = p.get( MainActivityViewModel.class);

        // get the binding instance
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        mBinding.setViewModel( vm );

        vm.getPersonsList().observe(this, new Observer<List<Person>>() {
            @Override
            public void onChanged( List<Person> ppl) {
                setupList( ppl );
            }
        });

        vm.loadPersonsList();
    }

    // refresh the listView
    void setupList( List<Person> ppl){
        // TODO: This is too expensive. Optimize
        LayoutInflater lf = this.getLayoutInflater();
        PersonListAdapter adapter  = new PersonListAdapter( lf );
        adapter.setData( ppl );
        mBinding.lvPersons.setAdapter( adapter );
    }


    // -----------------------------------------------------
    static class PersonListAdapter extends BaseAdapter {


        static final class ViewHolder{
            TextView tvGivenName;
            TextView tvFamilyName;
        }
        List<Person> mPersons;
        LayoutInflater mInflater;

        public PersonListAdapter( LayoutInflater lf ){
            super();
            this.mInflater = lf;
        }

        void setData( List<Person> inList){
            this.mPersons = inList;
        }

        @Override
        public int getCount() {
            return mPersons.size();
        }

        @Override
        public Object getItem(int position) {
            return mPersons.get( position );
        }

        @Override
        public long getItemId(int position) {
            return mPersons.get( position ).getId();
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if( convertView == null ){
                convertView = mInflater.inflate(R.layout.person_list_item,  parent,  false);
                ViewHolder vh = new ViewHolder();
                vh.tvGivenName = convertView.findViewById( R.id.txtGivenName);
                vh.tvFamilyName = convertView.findViewById( R.id.txtFamilyName);
                convertView.setTag( vh );
            }
            ViewHolder vh = (ViewHolder) convertView.getTag();
            Person p = this.mPersons.get( position );

            vh.tvFamilyName.setText( p.getFamilyName());
            vh.tvGivenName.setText( p.getGivenName());
            return convertView;
        }
    }
}