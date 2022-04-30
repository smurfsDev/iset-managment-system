<?php

use Illuminate\Database\Migrations\Migration;
use Illuminate\Database\Schema\Blueprint;
use Illuminate\Support\Facades\Schema;

class CreateDemandeMaterielsTable extends Migration
{
    /**
     * Run the migrations.
     *
     * @return void
     */
    public function up()
    {
        Schema::create('demande_materiels', function (Blueprint $table) {
            $table->id();
            $table->foreignId("idResponsableClub");
            $table->foreignId("idDestinataire");
            $table->date("dateEmploi");
            $table->date("dateDeRemise");
            $table->foreignId("idCategorie");
            $table->integer("status")->default(0);
            $table->string("reponse")->nullable();
            $table->timestamps();
            $table->foreign('idResponsableClub')->references('id')->on('users')->onDelete('cascade');
            $table->foreign('idDestinataire')->references('id')->on('users')->onDelete('cascade');
        });
    }

    /**
     * Reverse the migrations.
     *
     * @return void
     */
    public function down()
    {
        Schema::dropIfExists('demande_materiels');
    }
}
