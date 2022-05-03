<?php

use Illuminate\Database\Migrations\Migration;
use Illuminate\Database\Schema\Blueprint;
use Illuminate\Support\Facades\Schema;

class CreateDemandeMaterielMaterielsTable extends Migration
{
    /**
     * Run the migrations.
     *
     * @return void
     */
    public function up()
    {
        Schema::create('demande_materiel__materiels', function (Blueprint $table) {
            $table->id();
            $table->foreignId("idDemande");
            $table->foreignId("idMateriel");
            $table->integer("quantité")->default(0);
            $table->foreign('idDemande')->references('id')->on('demande_materiels')->onDelete('cascade');
            $table->foreign('idMateriel')->references('id')->on('materiels')->onDelete('cascade');

        });
    }

    /**
     * Reverse the migrations.
     *
     * @return void
     */
    public function down()
    {
        Schema::dropIfExists('demande_materiel__materiels');
    }
}
