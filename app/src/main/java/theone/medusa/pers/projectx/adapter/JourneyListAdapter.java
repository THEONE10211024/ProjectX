package theone.medusa.pers.projectx.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import theone.medusa.pers.projectx.R;
import theone.medusa.pers.projectx.bean.JourneyBean;

/**
 * Created by xiayong on 2016/1/1.
 */
public class JourneyListAdapter extends RecyclerView.Adapter<JourneyListAdapter.ItemViewHolder>{

    private List<JourneyBean> journeyBeans;
    public JourneyListAdapter(List<JourneyBean> journeyBeans){
        this.journeyBeans = journeyBeans;
    }
    @Override
    public ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_journey_item,null);
        return new ItemViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ItemViewHolder holder, int position) {
        JourneyBean journeyBean = journeyBeans.get(position);
        holder.startPlace.setText(journeyBean.getStartPlace());
        holder.endPlace.setText(journeyBean.getEndPlace());
        holder.serviceTime.setText(journeyBean.getTime());
        holder.carType.setText(journeyBean.getCarType());
    }

    @Override
    public int getItemCount() {
        return journeyBeans.size();
    }

    class ItemViewHolder extends RecyclerView.ViewHolder {
        public TextView startPlace;
        public TextView endPlace;
        public TextView serviceTime;
        public TextView carType;
        public ItemViewHolder(View itemView) {
            super(itemView);
            startPlace = (TextView) itemView.findViewById(R.id.tv_start_place);
            endPlace = (TextView) itemView.findViewById(R.id.tv_end_place);
            serviceTime = (TextView) itemView.findViewById(R.id.tv_time);
            carType = (TextView) itemView.findViewById(R.id.tv_car);
        }
    }
}
