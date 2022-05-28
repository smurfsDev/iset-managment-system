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
            $table->longText('file')->nullable();
            $table->foreignId('classes_id');
            $table->foreignId('idResponsable');
            $table->timestamps();
            $table->foreign('idResponsable')->references('id')->on('users')->onDelete('cascade');
            $table->foreign('classes_id')->references('id')->on('classes')->onDelete('cascade');
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
