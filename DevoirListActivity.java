package com.example.pfe;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.*;
import java.util.ArrayList;
import java.util.List;

public class DevoirListActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private DevoirAdapter adapter;
    private List<Devoir> devoirList = new ArrayList<>();
    private List<String> formationsIds = new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_devoir_list);

        recyclerView = findViewById(R.id.recyclerViewDevoirs);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new DevoirAdapter(this, devoirList);
        recyclerView.setAdapter(adapter);

        chargerFormationsInscrites();
    }

    private void chargerFormationsInscrites() {
        String userId = FirebaseAuth.getInstance().getCurrentUser().getUid();
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference("inscriptions");
        ref.orderByChild("idEtudiant").equalTo(userId)
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot snapshot) {
                        formationsIds.clear();
                        for (DataSnapshot ds : snapshot.getChildren()) {
                            String idForm = ds.child("idFormation").getValue(String.class);
                            if (idForm != null) formationsIds.add(idForm);
                        }
                        if (formationsIds.isEmpty()) {
                            Toast.makeText(DevoirListActivity.this,
                                    "Vous n'êtes inscrit à aucune formation.",
                                    Toast.LENGTH_SHORT).show();
                        } else {
                            chargerDevoirs();
                        }
                    }

                    @Override
                    public void onCancelled(DatabaseError error) {
                        Log.e("MesDevoirs", error.getMessage());
                    }
                });
    }

    private void chargerDevoirs() {
        DatabaseReference devoirsRef = FirebaseDatabase.getInstance().getReference("devoirs");
        devoirsRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snap) {
                devoirList.clear();
                for (DataSnapshot formationSnap : snap.getChildren()) {
                    for (DataSnapshot semestreSnap : formationSnap.getChildren()) {
                        for (DataSnapshot devoirSnap : semestreSnap.getChildren()) {
                            Devoir d = devoirSnap.getValue(Devoir.class);
                            if (d != null && formationsIds.contains(d.getIdFormation())) {
                                d.setId(devoirSnap.getKey());
                                d.setNomFormation(formationSnap.getKey());
                                d.setSemestre(semestreSnap.getKey());
                                devoirList.add(d);
                            }
                        }
                    }
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(DatabaseError error) {
                Log.e("MesDevoirs", error.getMessage());
            }
        });
    }
}
