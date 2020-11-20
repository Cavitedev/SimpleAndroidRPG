package com.example.simplerpg.application.party.partyEdition.heroProfile.abilities;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.simplerpg.R;

import de.blox.treeview.BaseTreeAdapter;
import de.blox.treeview.TreeNode;
import de.blox.treeview.TreeView;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link AbilityTreeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AbilityTreeFragment extends Fragment {
    //TODO ability trees
    private int nodeCount = 0;

    public AbilityTreeFragment() {
        // Required empty public constructor
    }

    public static AbilityTreeFragment newInstance(Bundle bundle) {
        AbilityTreeFragment fragment = new AbilityTreeFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_ability_tree, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        TreeView treeView = view.findViewById(R.id.abilityTree_treeView);

        BaseTreeAdapter adapter = new BaseTreeAdapter<ViewHolder>(this.getContext(), R.layout.ability_preview) {
            @NonNull
            @Override
            public ViewHolder onCreateViewHolder(View view) {
                return new ViewHolder(view);
            }

            @Override
            public void onBindViewHolder(ViewHolder viewHolder, Object data, int position) {
                viewHolder.mTextView.setText(data.toString());
            }
        };
        treeView.setAdapter(adapter);

        // example tree
        TreeNode rootNode = new TreeNode(getNodeText());
        final TreeNode root1child1 = new TreeNode(getNodeText());
        rootNode.addChild(root1child1);
        final TreeNode root1child2 = new TreeNode(getNodeText());
        root1child1.addChild(root1child2);
        final TreeNode root1child3 = new TreeNode(getNodeText());
        root1child2.addChild(root1child3);

        adapter.setRootNode(rootNode);
    }

    private String getNodeText() {
        return nodeCount++ + "/5";
    }

    private class ViewHolder {
        TextView mTextView;

        ViewHolder(View view) {
            mTextView = view.findViewById(R.id.abilityPreview_lvl);
        }
    }
}