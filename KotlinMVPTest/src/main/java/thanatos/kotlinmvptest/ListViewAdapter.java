package thanatos.kotlinmvptest;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import thanatos.kotlinmodel.net.Images;
import thanatos.kotlinmodel.net.imageloader.ImageLoader;
import thanatos.kotlinmodel.net.imageloader.utils.DoubleCache;

/**
 * Created on 2017/2/20.
 * 作者：by thanatos
 * 作用：
 */
public class ListViewAdapter extends BaseAdapter {

    private String[] list;

    private Context context;

    public ListViewAdapter(Context context,String[] list) {
        this.list=list;
        this.context=context;
    }


    @Override
    public int getCount() {
        return list.length;
    }


    @Override
    public Object getItem(int position) {
        return list[position];
    }


    @Override
    public long getItemId(int position) {
        return position;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView==null){
            convertView=View.inflate(context,R.layout.item,null);
            holder=new ViewHolder(convertView);
            convertView.setTag(holder);
        }else {
            holder= (ViewHolder) convertView.getTag();
        }
        ImageLoader.getInstance(new DoubleCache("test",60),true).load(Images.baseUrl+list[position],holder
                .imageView);
        return convertView;
    }


    private static class ViewHolder{
        ImageView imageView;

         ViewHolder(View view) {
            imageView= (ImageView) view.findViewById(R.id.item_iv);
        }
    }
}