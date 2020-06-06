package com.gaurav.madassignment;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.ClipData;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;

import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements RecyclerItemTouchHelper.RecyclerItemTouchHelperListener {

    RecyclerView recyclerView;
    ToDoAdapter adapter;
    List<ToDoClass> toDoList;
    RelativeLayout parentLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recycler_view);
        parentLayout = findViewById(R.id.parent_layout);

        toDoList  = new ArrayList<ToDoClass>();
        toDoList.add(new ToDoClass("1","MAD Assignment","Due: 08/06/2020","Assignment on card view and recycler view."));
        toDoList.add(new ToDoClass("2","Game Programming Quiz","Due: 04/06/2020","Module 1 and Module 2."));
        toDoList.add(new ToDoClass("3","MAD Assignment 2","Due: 08/06/2020","Assignment on Firebase and PhoneGap."));
        toDoList.add(new ToDoClass("4","IoT Project Review","Due: 06/06/2020","Prepare documentation."));
        toDoList.add(new ToDoClass("5","TARP Project Review","Due: 05/06/2020","Prepare the presentation and the demo"));
        toDoList.add(new ToDoClass("1","MAD Assignment","Due: 08/06/2020","Assignment on card view and recycler view."));
        toDoList.add(new ToDoClass("2","Game Programming Quiz","Due: 04/06/2020","Module 1 and Module 2."));
        toDoList.add(new ToDoClass("3","MAD Assignment 2","Due: 08/06/2020","Assignment on Firebase and PhoneGap."));
        toDoList.add(new ToDoClass("4","IoT Project Review","Due: 06/06/2020","Prepare documentation."));
        toDoList.add(new ToDoClass("5","TARP Project Review","Due: 05/06/2020","Prepare the presentation and the demo"));
        adapter = new ToDoAdapter(getApplicationContext(),toDoList);

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);

        //Touch and Swipe Listener
        ItemTouchHelper.SimpleCallback itemTouchHelperCallback = new RecyclerItemTouchHelper(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT, this);
        new ItemTouchHelper(itemTouchHelperCallback).attachToRecyclerView(recyclerView);

    }

    @Override
    public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction, int position) {
        if (viewHolder instanceof ToDoAdapter.MyViewHolder) {
            // get the removed item name to display it in snack bar
            String name = toDoList.get(viewHolder.getAdapterPosition()).getName();

            // backup of removed item for undo purpose
            final ToDoClass deletedItem = toDoList.get(viewHolder.getAdapterPosition());
            final int deletedIndex = viewHolder.getAdapterPosition();

            // remove the item from recycler view
            adapter.removeItem(viewHolder.getAdapterPosition());

            // showing snack bar with Undo option
            Snackbar snackbar = Snackbar
                    .make(parentLayout, name + " removed from list!", Snackbar.LENGTH_LONG);
            snackbar.setAction("UNDO", new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    // undo is selected, restore the deleted item
                    adapter.restoreItem(deletedItem, deletedIndex);
                }
            });
            snackbar.setActionTextColor(Color.YELLOW);
            snackbar.show();
        }
    }
}
