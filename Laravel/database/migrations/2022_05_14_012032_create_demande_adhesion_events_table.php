<?php

use Illuminate\Database\Migrations\Migration;
use Illuminate\Database\Schema\Blueprint;
use Illuminate\Support\Facades\Schema;

class CreateDemandeAdhesionEventsTable extends Migration
{
    /**
     * Run the migrations.
     *
     * @return void
     */
    public function up()
    {
        Schema::create('demande_adhesion_events', function (Blueprint $table) {
            $table->id();
            $table->foreignId("idStudent");
            $table->foreignId("idEvent");
            $table->integer("status")->default(0);
            $table->foreign('idStudent')->references('id')->on('users')->onDelete('cascade');
            $table->foreign('idEvent')->references('id')->on('demande_events')->onDelete('cascade');
            $table->timestamps();
        });
    }

    /**
     * Reverse the migrations.
     *
     * @return void
     */
    public function down()
    {
        Schema::dropIfExists('demande_adhesion_events');
    }
}
