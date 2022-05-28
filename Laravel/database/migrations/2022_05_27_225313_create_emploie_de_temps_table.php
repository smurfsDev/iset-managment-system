<?php

use Illuminate\Database\Migrations\Migration;
use Illuminate\Database\Schema\Blueprint;
use Illuminate\Support\Facades\Schema;

class CreateEmploieDeTempsTable extends Migration
{
    /**
     * Run the migrations.
     *
     * @return void
     */
    public function up()
    {
        Schema::create('emploie_de_temps', function (Blueprint $table) {
            $table->id();
            $table->string('title');
            $table->foreignId('classes_id');
            $table->foreign('classes_id')->references('id')->on('classes');
            $table->foreignId('idResponsable');
            $table->foreign('idResponsable')->references('id')->on('users');
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
        Schema::dropIfExists('emploie_de_temps');
    }
}
