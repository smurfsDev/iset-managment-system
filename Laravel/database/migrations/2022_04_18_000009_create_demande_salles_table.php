<?php

use Illuminate\Database\Migrations\Migration;
use Illuminate\Database\Schema\Blueprint;
use Illuminate\Support\Facades\Schema;

class CreateDemandeSallesTable extends Migration
{
    /**
     * Run the migrations.
     *
     * @return void
     */
    public function up()
    {
        Schema::create('demande_salles', function (Blueprint $table) {
            $table->id();
            $table->foreignId("idResponsable");
            $table->foreignId("idSalle");
            $table->date("dateEmploi");
            $table->date("dateDeRemise");
    
            $table->foreign('idResponsable')
                ->references('id')
                ->on('users')
                ->onDelete('cascade');
            $table->foreign('idSalle')
                ->references('id')
                ->on('salles')
                ->onDelete('cascade');
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
        Schema::dropIfExists('demande_salles');
    }
}
