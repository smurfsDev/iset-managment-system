<?php

use Illuminate\Database\Migrations\Migration;
use Illuminate\Database\Schema\Blueprint;
use Illuminate\Support\Facades\Schema;

class CreateDemandeEventsTable extends Migration
{
    /**
     * Run the migrations.
     *
     * @return void
     */
    public function up()
    {
        Schema::create('demande_events', function (Blueprint $table) {
            $table->id();
            $table->foreignId("responsableClubId");
            $table->foreignId("administrateurId");
            $table->string("nomEvent");
            $table->date("dateEvent");
            $table->string("description");
            $table->integer("status")->default(0);
           // $table->string("reponse")->nullable();
            $table->timestamps();
            $table->foreign('responsableClubId')->references('id')->on('users')->onDelete('cascade');
            $table->foreign('administrateurId')->references('id')->on('users')->onDelete('cascade');
      
        });
    }

    /**
     * Reverse the migrations.
     *
     * @return void
     */
    public function down()
    {
        Schema::dropIfExists('demande_events');
    }
}
