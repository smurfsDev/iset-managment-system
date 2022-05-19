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
            $table->string('type');
            $table->foreignId('idResponsable');
            $table->foreign('idResponsable')->references('id')->on('users');
            $table->foreignId('idAdmin');
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
