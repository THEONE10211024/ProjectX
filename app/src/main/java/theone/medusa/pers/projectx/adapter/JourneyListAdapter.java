package theone.medusa.pers.projectx.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import theone.medusa.pers.projectx.R;
import theone.medusa.pers.projectx.bean.JourneyBean;

/**
 * Created by xiayong on 2016/1/1.
 */
public class JourneyListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{
    private static final int IS_FOOTER = 3;
//    private static final int IS_HEADER = 2;
    private static final int IS_NORMAL = 1;

    private List<JourneyBean> journeyBeans;
    public JourneyListAdapter(List<JourneyBean> journeyBeans){
        this.journeyBeans = journeyBeans;
    }
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if(viewType == IS_NORMAL){
            View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_journey_item,null);
            return new ItemViewHolder(itemView);
        }else{
            View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_journey_footer,null);
            return new FooterViewHolder(itemView);
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if(holder instanceof ItemViewHolder){
            JourneyBean journeyBean = journeyBeans.get(position);
            ItemViewHolder itemViewHolder = (ItemViewHolder) holder;
            itemViewHolder.startPlace.setText(journeyBean.getStartPlace());
            itemViewHolder.endPlace.setText(journeyBean.getEndPlace());
            itemViewHolder.serviceTime.setText(journeyBean.getTime());
            itemViewHolder.carType.setImageResource(journeyBean.getCarTypeSrcId());
        }
    }

   /* @Override
    public void onBindViewHolder(ItemViewHolder holder, int position) {
        JourneyBean journeyBean = journeyBeans.get(position);
        holder.startPlace.setText(journeyBean.getStartPlace());
        holder.endPlace.setText(journeyBean.getEndPlace());
        holder.serviceTime.setText(journeyBean.getTime());
        holder.carType.setImageResource(journeyBean.getCarTypeSrcId());
    }*/

    @Override
    public int getItemCount() {
        return journeyBeans.size()+1;
    }

    @Override
    public int getItemViewType(int position) {
        if (position == journeyBeans.size()){
            return IS_FOOTER;
        }else{
            return IS_NORMAL;
        }
    }
    class ItemViewHolder extends RecyclerView.ViewHolder {
        public TextView startPlace;
        public TextView endPlace;
        public TextView serviceTime;
        public ImageView carType;
        public ItemViewHolder(View itemView) {
            super(itemView);
            startPlace = (TextView) itemView.findViewById(R.id.tv_start_place);
            endPlace = (TextView) itemView.findViewById(R.id.tv_end_place);
            serviceTime = (TextView) itemView.findViewById(R.id.tv_time);
            carType = (ImageView) itemView.findViewById(R.id.img_car);
        }
    }
    class FooterViewHolder extends RecyclerView.ViewHolder {
        public TextView footerTxt;
        public FooterViewHolder(View itemView) {
            super(itemView);
            footerTxt = (TextView) itemView.findViewById(R.id.tv_no_more_orders);
        }
    }
}
