package au.edu.jcu.mobile_technology.scott.alexander.assignment.view;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import au.edu.jcu.mobile_technology.scott.alexander.assignment.R;
import au.edu.jcu.mobile_technology.scott.alexander.assignment.core.HighScore;
import au.edu.jcu.mobile_technology.scott.alexander.assignment.dao.SQLiteHighScoreDAO;

/**
 * HighScore List View Adapter
 */
public class HighScoreListViewAdapter extends BaseAdapter {
    //Application Context
    private Context context;

    //Data Source
    SQLiteHighScoreDAO dao;

    /**
     * Constructs a HighScoreListViewAdapter with a specified context
     *
     * @param context Application context
     */
    public HighScoreListViewAdapter(Context context) {
        this.context = context;
        dao = SQLiteHighScoreDAO.getInstance(context);
    }

    /**
     * Gets the total number of HighScores
     *
     * @return Total HighScores
     */
    @Override
    public int getCount() {
        return dao.getAll().size();
    }

    /**
     * Gets the HighScore at a specified index
     *
     * @param index Index
     * @return HighScore
     */
    @Override
    public Object getItem(int index) {
        return dao.getAll().get(index);
    }

    /**
     * Gets the ItemId at a specified index
     *
     * @param index Item Index
     * @return itemId
     */
    @Override
    public long getItemId(int index) {
        return 0;
    }

    /**
     * Gets the individual list view items as required by the parent view
     *
     * @param position    Index of the view to obtain
     * @param convertView Existing view to recycle
     * @param parent      Parent view container to position in
     * @return New or recycled HighScore item view
     */
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            LayoutInflater inflater = ((Activity) context).getLayoutInflater();
            convertView = inflater.inflate(R.layout.high_score_fragment, parent, false);
        }

        TextView rank = (TextView) convertView.findViewById(R.id.rank);
        TextView name = (TextView) convertView.findViewById(R.id.name);
        TextView score = (TextView) convertView.findViewById(R.id.score);

        HighScore model = (HighScore) getItem(position);

        rank.setText(String.valueOf(position + 1));
        name.setText(model.getName());
        score.setText(String.valueOf(model.getScore()));
        return convertView;
    }
}
