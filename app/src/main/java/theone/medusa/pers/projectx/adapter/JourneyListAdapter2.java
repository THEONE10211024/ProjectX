package theone.medusa.pers.projectx.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.karumi.headerrecyclerview.HeaderRecyclerViewAdapter;

import theone.medusa.pers.projectx.R;
import theone.medusa.pers.projectx.bean.JourneyBean;

/**
 * Created by xiayong on 2016/1/22.
 */
public class JourneyListAdapter2  extends HeaderRecyclerViewAdapter<RecyclerView.ViewHolder, String, JourneyBean, String> {
    @Override
    protected RecyclerView.ViewHolder onCreateHeaderViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    protected RecyclerView.ViewHolder onCreateItemViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_journey_item,null);
        return new ItemViewHolder(itemView);
    }

    @Override
    protected RecyclerView.ViewHolder onCreateFooterViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_journey_footer,null);
        return new FooterViewHolder(itemView);
    }

    @Override
    protected void onBindHeaderViewHolder(RecyclerView.ViewHolder holder, int position) {

    }

    @Override
    protected void onBindItemViewHolder(RecyclerView.ViewHolder holder, int position) {
        JourneyBean journeyBean = getItem(position);
        ItemViewHolder itemViewHolder = (ItemViewHolder) holder;
        itemViewHolder.startPlace.setText(journeyBean.getStartPlace());
        itemViewHolder.endPlace.setText(journeyBean.getEndPlace());
        itemViewHolder.serviceTime.setText(journeyBean.getTime());
        itemViewHolder.carType.setImageResource(journeyBean.getCarTypeSrcId());
    }

    @Override
    protected void onBindFooterViewHolder(RecyclerView.ViewHolder holder, int position) {
        FooterViewHolder footerViewHolder = (FooterViewHolder) holder;
        footerViewHolder.footerTxt.setText("没有更多订单了");
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
