package com.example.pluginstest;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by gerrylin on 2018/3/29.
 */

public class AppAdapter {
    private LayoutInflater layoutInflater;
    private List<AppList> listStorage;

    public AppAdapter(Context context, List<AppList> customizedListView) {
        layoutInflater =(LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        listStorage = customizedListView;
    }


    public int getCount() {
        return listStorage.size();
    }


    public Object getItem(int position) {
        return position;
    }


    public long getItemId(int position) {
        return position;
    }


    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder listViewHolder;
        if(convertView == null){
            listViewHolder = new ViewHolder();
            //convertView = layoutInflater.inflate(R.layout.installed_app_list, parent, false);

            //listViewHolder.textInListView = (TextView)convertView.findViewById(R.id.list_app_name);
            //listViewHolder.imageInListView = (ImageView)convertView.findViewById(R.id.app_icon);
            convertView.setTag(listViewHolder);
        }else{
            listViewHolder = (ViewHolder)convertView.getTag();
        }
        listViewHolder.textInListView.setText(listStorage.get(position).getName());
        listViewHolder.imageInListView.setImageDrawable(listStorage.get(position).getIcon());

        return convertView;
    }

    static class ViewHolder{

        TextView textInListView;
        ImageView imageInListView;
    }
}
