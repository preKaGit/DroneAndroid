package com.example.droneapp;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

import java.util.ArrayList;

//////Only for test//////
import java.util.Random;


public class GraphActivity extends Activity {
    public static final int NUMBER_OF_DATA_SETS = 5, SIZE_OF_DATA_SET = 10, LINE_THICKNESS = 7, ALPHA_ZERO = 0, ALPHA_MAX = 100, DATA_POINT_RADIUS = 8;

    private Object dataFromDatabase;
    ArrayList<LineGraphSeries> lineGraphSeries;

    private GraphView chart;
    private String description;
    protected Button button1, button2, button3, button4, button5, button6;
    protected ImageButton buttonLoadSet;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.acticity_graph);
        buttonLoadSet = findViewById(R.id.buttonLoadDataSetMenu);
        chart = findViewById(R.id.chart);

        if(lineGraphSeries == null) {
           // liveChart();

        } else {
            loadChart(NUMBER_OF_DATA_SETS, description);

        }


    }

    @Override
    protected void onResume() {
        super.onResume();

        loadChart(NUMBER_OF_DATA_SETS, description);

        View decorView = getWindow().getDecorView();
        int uiOptions = View.SYSTEM_UI_FLAG_FULLSCREEN;
        decorView.setSystemUiVisibility(uiOptions);

    }

    ///////////////////////////////////Load chart///////////////////////////////////
    //   1.  Sorts all buttons, colors and incoming data graphs into ArrayLists   //
    //   2.  Loops through all data sets and assigns button & visual information  //
    //       and onClickListener                                                  //
    //   3.  Plots chart                                                          //
    ////////////////////////////////////////////////////////////////////////////////

    private void loadChart(int numberOfDataSets, String nameOfLoadedDataSet) {
        button1 = findViewById(R.id.button1);
        button2 = findViewById(R.id.button2);
        button3 = findViewById(R.id.button3);
        button4 = findViewById(R.id.button4);
        button5 = findViewById(R.id.button5);
        button6 = findViewById(R.id.button6);

        ArrayList<Button> buttons = new ArrayList<>();
        buttons.add(button1);
        buttons.add(button2);
        buttons.add(button3);
        buttons.add(button4);
        buttons.add(button5);
        buttons.add(button6);

        ArrayList<String> dataLabels = getArrayOfDataLabels();
        final ArrayList<Integer> colors = getArrayOfColors();
        lineGraphSeries = new ArrayList<>();

        for (int index = 0; index < numberOfDataSets; index++) {
            lineGraphSeries.add(getLineGraph());
            buttons.get(index).setText(dataLabels.get(index));
            buttons.get(index).setTextColor(Color.WHITE);
            final int extendedScopeIndex = index;
            buttons.get(index).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    toggleGraph(lineGraphSeries.get(extendedScopeIndex), colors.get(extendedScopeIndex));
                }
            });
            lineGraphSeries.get(index).setThickness(LINE_THICKNESS);
            lineGraphSeries.get(index).setColor(Color.alpha(ALPHA_ZERO));
            lineGraphSeries.get(index).setDrawDataPoints(true);
            lineGraphSeries.get(index).setDataPointsRadius(DATA_POINT_RADIUS);

        }

        for(int index = 0; index < NUMBER_OF_DATA_SETS; index++) {
            chart.addSeries(lineGraphSeries.get(index));
        }
        description = getNameOfDataSet();
        setupChart();

    }

    //////////////////////////////////setupChart//////////////////////////////////////
    //                        Setts all chart settings                              //
    //////////////////////////////////////////////////////////////////////////////////

    private void setupChart() {
        //Chart Title
        chart.setTitle(description);
        chart.setTitleColor(Color.WHITE);

        //Grid and axis colors
        chart.getGridLabelRenderer().setGridColor(Color.WHITE);
        chart.getGridLabelRenderer().setVerticalLabelsColor(Color.WHITE);
        chart.getGridLabelRenderer().setHorizontalLabelsColor(Color.WHITE);

        //Makes zooming and scrolling possible
        chart.getViewport().setScalable(true);
        chart.getViewport().setScalableY(true);

    }

    ///////////////////////////////getArrayOfColors///////////////////////////////////
    //                     Returns an ArrayList of Labels                           //
    //////////////////////////////////////////////////////////////////////////////////
    // Will have to be changed when actual data is coming in!!!

    private ArrayList<String> getArrayOfDataLabels() {
        ArrayList<String> labels = new ArrayList<>();
        labels.add(0, "Roll flex");
        labels.add(0, "Yaw stability");
        labels.add(0, "Hovering stability X");
        labels.add(0, "Hovering stability Y");
        labels.add(0, "Battery output");
        labels.add(0, "Altitude");

        return labels;
    }

    ///////////////////////////////getArrayOfColors///////////////////////////////////
    //                     Returns an ArrayList of colors                           //
    //////////////////////////////////////////////////////////////////////////////////
    // Will have to be changed when actual data is coming in!!!

    private ArrayList<Integer> getArrayOfColors() {
        ArrayList<Integer> colors = new ArrayList<>();
        colors.add(0, Color.CYAN);
        colors.add(0, Color.YELLOW);
        colors.add(0, Color.MAGENTA);
        colors.add(0, Color.RED);
        colors.add(0, Color.GREEN);

        return colors;
    }

    ///////////////////////////////////ToggleGraph////////////////////////////////////
    //   Checks if graph is visible or not and toggles it's visibility accordingly  //
    //////////////////////////////////////////////////////////////////////////////////

    private void toggleGraph(LineGraphSeries lineDataSet, int color) {
        if (lineDataSet.getColor() == color){
            lineDataSet.setColor(Color.alpha(ALPHA_ZERO));

        } else {
            lineDataSet.setColor(color);

        }
//        lineDataSet.notifyDataSetChanged();
//        chart.notifyDataSetChanged();
//        chart.invalidate();

        this.onResume();
    }

    ////////////////////////////////////GetLineGraph///////////////////////////////////
    //            Populate line graph with data points and return result             //
    ///////////////////////////////////////////////////////////////////////////////////

    private LineGraphSeries getLineGraph(){
        LineGraphSeries<DataPoint> lineGraph = new LineGraphSeries<>(getDataForGraph());
        return lineGraph;
    }

    //////////////////////////////////getNameOfDataSet/////////////////////////////////
    //                           Get the name of the data set                        //
    ///////////////////////////////////////////////////////////////////////////////////

    private String getNameOfDataSet() {
        return "Random data set";
    }

    ///////////////////////////////////GetDataForGraph/////////////////////////////////
    //          Create random data for testing without real data available           //
    ///////////////////////////////////////////////////////////////////////////////////
    // Needs update to query data from database (instead of random) when available!!!

    private DataPoint[] getDataForGraph(){
        DataPoint[] graphDataPoints = new DataPoint[SIZE_OF_DATA_SET];

        for (int index = 0; index < SIZE_OF_DATA_SET; index++ ){

            Random random = new Random();
            DataPoint point = new DataPoint(index,(100*(random.nextFloat())*(100*(random.nextFloat())))/3);

            graphDataPoints[index] = point;
        }
        return graphDataPoints;
    }
}
