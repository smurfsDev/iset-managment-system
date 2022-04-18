<?php

use Illuminate\Database\Migrations\Migration;
use Illuminate\Database\Schema\Blueprint;
use Illuminate\Support\Facades\Schema;

class CreateDemandeClassesTable extends Migration
{
    /**
     * Run the migrations.
     *
     * @return void
     */
    public function up()
    {
        Schema::create('demande_classes', function (Blueprint $table) {
            $table->id();
            $table->foreignId("idResponsable");
            $table->foreignId("idClasse");
            $table->dateTime("dateEmploi");
            $table->dateTime("dateDeRemise");
            $table->timestamps();
            $table->foreign('idResponsable')->references('id')->on('users')->onDelete('cascade');
            $table->foreign('idClasse')->references('id')->on('classes')
                               ->onDelete('cascade');
        });
    }

    /**
     * Reverse the migrations.
     *
     * @return void
     */
    public function down()
    {
        Schema::dropIfExists('demande_classes');
    }
}
