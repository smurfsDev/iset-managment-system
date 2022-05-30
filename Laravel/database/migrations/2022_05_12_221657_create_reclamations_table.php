<?php

use Illuminate\Database\Migrations\Migration;
use Illuminate\Database\Schema\Blueprint;
use Illuminate\Support\Facades\Schema;

class CreateReclamationsTable extends Migration
{
    /**
     * Run the migrations.
     *
     * @return void
     */
    public function up()
    {
        Schema::create('reclamations', function (Blueprint $table) {
            $table->id();
            $table->string('titre');
            $table->string('description');
            $table->longText('file')->nullable();
            $table->foreignId('type_cats_id');
           
            $table->foreignId('idResponsable');
            $table->foreign('idResponsable')->references('id')->on('users');
            $table->foreignId('idAdmin')->nullable();
            $table->foreign('idAdmin')->references('id')->on('users');
            $table->text('reponse')->nullable();
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
        Schema::dropIfExists('reclamations');
    }
}
