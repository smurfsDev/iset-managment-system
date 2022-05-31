<?php

use Illuminate\Database\Migrations\Migration;
use Illuminate\Database\Schema\Blueprint;
use Illuminate\Support\Facades\Schema;

class CreateDocumentsTable extends Migration
{
    /**
     * Run the migrations.
     *
     * @return void
     */
    public function up()
    {
        Schema::create('documents', function (Blueprint $table) {
            $table->id();
            $table->string('nom');
            $table->foreignId('classes_id')->nullable();
            $table->foreign('classes_id')->references('id')->on('classes');
            $table->foreignId('document_categories_id');
            // $table->foreign('document_categories_id')->references('id')->on('document_categories_id');
            $table->longText('file');
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
        Schema::dropIfExists('documents');
    }
}
