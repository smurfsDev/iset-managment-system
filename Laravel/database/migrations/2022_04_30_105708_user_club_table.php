<?php

use Illuminate\Database\Migrations\Migration;
use Illuminate\Database\Schema\Blueprint;
use Illuminate\Support\Facades\Schema;

class UserClubTable extends Migration
{
    /**
     * Run the migrations.
     *
     * @return void
     */
    public function up()
    {
        Schema::create('demande_adhesion_club', function (Blueprint $table) {
            $table->id();
            $table->smallInteger('status');
            $table->foreignId('user_id');
            $table->foreignId('club_id');
            $table->foreignId('admin_id')->nullable();
            $table->foreign('user_id')->references('id')->on('users');
            $table->foreign('club_id')->references('id')->on('clubs');
            $table->foreign('admin_id')->references('id')->on('users');
            $table->text('message');
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
        Schema::dropIfExists('club_user');
    }
}
