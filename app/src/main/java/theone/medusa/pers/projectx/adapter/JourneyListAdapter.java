package theone.medusa.pers.projectx.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by xiayong on 2016/1/1.
 */
public class JourneyListAdapter extends RecyclerView.Adapter<JourneyListAdapter.ItemViewHolder>{

    @Override
    public ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(ItemViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    class ItemViewHolder extends RecyclerView.ViewHolder {
        private TextView mTextView;
        public ItemViewHolder(View itemView) {
            super(itemView);
        }
    }
}
