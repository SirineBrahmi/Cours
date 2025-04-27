package com.example.pfe;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class DevoirDetailsActivity extends AppCompatActivity {
    private TextView textTitre, textDescription, textDateLimite;
    private Button btnOuvrirPdf;
    private Devoir devoir;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_devoir_details);

        textTitre = findViewById(R.id.textViewTitreDetail);
        textDescription = findViewById(R.id.textViewDescriptionDetail);
        textDateLimite = findViewById(R.id.textViewDateLimiteDetail);
        btnOuvrirPdf = findViewById(R.id.buttonOuvrirPdf);

        Devoir devoir = (Devoir) getIntent().getSerializableExtra("devoir");

        if (devoir != null) {
            textTitre.setText(devoir.getTitre());
            textDescription.setText(devoir.getDescription());
            textDateLimite.setText("Date limite : " + devoir.getDateLimite());

            btnOuvrirPdf.setOnClickListener(v -> {
                Intent intent = new Intent(DevoirDetailsActivity.this, PdfViewerActivity.class);
                intent.putExtra("PDF_URL", devoir.getFileURL()); // Utilisez "PDF_URL" au lieu de "fileURL"
                startActivity(intent);
            });
        }
    }
}
