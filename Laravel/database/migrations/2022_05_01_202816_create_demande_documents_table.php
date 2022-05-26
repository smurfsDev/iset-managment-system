<?php

use Illuminate\Database\Migrations\Migration;
use Illuminate\Database\Schema\Blueprint;
use Illuminate\Support\Facades\Schema;

class CreateDemandeDocumentsTable extends Migration
{
    /**
     * Run the migrations.
     *
     * @return void
     */
    public function up()
    {
        Schema::create('demande_documents', function (Blueprint $table) {
            $table->id();
            $table->string('titre');
            $table->string('description');
            $table->foreignId('document_categories_id');
            $table->longText('file')->nullable();
            $table->foreignId('idResponsable')->nullable();
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
        Schema::dropIfExists('demande_documents');
    }
}
